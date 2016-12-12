package com.stk.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Comparator;

public class AbFileUtil {

	/** 默认APP根目录. */
	private static String downloadRootDir = null;

	/** 默认下载图片文件目录. */
	private static String imageDownloadDir = null;

	/** 默认下载文件目录. */
	private static String fileDownloadDir = null;

	/** 默认缓存目录. */
	private static String cacheDownloadDir = null;

	/** 默认下载数据库文件的目录. */
	private static String dbDownloadDir = null;

	/** 剩余空间大于200M才使用SD缓存. */
	private static int freeSdSpaceNeededToCache = 200 * 1024 * 1024;

	/**
	 * 描述：获取src中的图片资源.
	 *
	 * @param src
	 *            图片的src路径，如（“image/arrow.png”）
	 * @return Bitmap 图片
	 */
	public static Bitmap getBitmapFromSrc(String src) {
		Bitmap bit = null;
		try {
			bit = BitmapFactory.decodeStream(AbFileUtil.class.getResourceAsStream(src));
		} catch (Exception e) {
		}
		return bit;
	}

	/**
	 * 描述：获取Asset中的图片资源.
	 *
	 * @param context
	 *            the context
	 * @param fileName
	 *            the file name
	 * @return Bitmap 图片
	 */
	public static Bitmap getBitmapFromAsset(Context context, String fileName) {
		Bitmap bit = null;
		try {
			AssetManager assetManager = context.getAssets();
			InputStream is = assetManager.open(fileName);
			bit = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
		}
		return bit;
	}

	/**
	 * 描述：从sd卡中的文件读取到byte[].
	 *
	 * @param path
	 *            sd卡中文件路径
	 * @return byte[]
	 */
	public static byte[] getByteArrayFromSD(String path) {
		byte[] bytes = null;
		ByteArrayOutputStream out = null;
		try {
			File file = new File(path);
			// SD卡是否存在
			if (!isCanUseSD()) {
				return null;
			}
			// 文件是否存在
			if (!file.exists()) {
				return null;
			}

			long fileSize = file.length();
			if (fileSize > Integer.MAX_VALUE) {
				return null;
			}

			FileInputStream in = new FileInputStream(path);
			out = new ByteArrayOutputStream(1024);
			byte[] buffer = new byte[1024];
			int size = 0;
			while ((size = in.read(buffer)) != -1) {
				out.write(buffer, 0, size);
			}
			in.close();
			bytes = out.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
		return bytes;
	}

	/**
	 * 描述：将byte数组写入文件.
	 *
	 * @param path
	 *            the path
	 * @param content
	 *            the content
	 * @param create
	 *            the create
	 */
	public static void writeByteArrayToSD(String path, byte[] content, boolean create) {

		FileOutputStream fos = null;
		try {
			File file = new File(path);
			// SD卡是否存在
			if (!isCanUseSD()) {
				return;
			}
			// 文件是否存在
			if (!file.exists()) {
				if (create) {
					File parent = file.getParentFile();
					if (!parent.exists()) {
						parent.mkdirs();
						file.createNewFile();
					}
				} else {
					return;
				}
			}
			fos = new FileOutputStream(path);
			fos.write(content);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 将bitmap写入文件.
	 * 
	 * @param path
	 * @param bitmap
	 *            png
	 */
	public static void writeBitmapToSD(String path, Bitmap bitmap, boolean create) {

		FileOutputStream fos = null;
		try {
			File file = new File(path);
			// SD卡是否存在
			if (!isCanUseSD()) {
				return;
			}
			// 文件是否存在
			if (!file.exists()) {
				if (create) {
					File parent = file.getParentFile();
					if (!parent.exists()) {
						parent.mkdirs();
						file.createNewFile();
					}
				}
			}
			fos = new FileOutputStream(path);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 拷贝Assets目录内容到sd卡目录
	 * 
	 * @param context
	 * @param assetDir
	 *            "dir"
	 * @param outDir
	 *            完整sd卡路径
	 */
	public static void copyAssets2SD(Context context, String assetDir, String outDir) {
		String[] files;
		try {
			files = context.getAssets().list(assetDir);
			File outDirFile = new File(outDir);
			if (!outDirFile.exists()) {
				outDirFile.mkdirs();
			}

			for (int i = 0; i < files.length; i++) {
				String fileName = files[i];

				String[] filesChild = context.getAssets().list(fileName);
				if (filesChild != null && filesChild.length > 0) {
					copyAssets2SD(context, fileName, outDir + "/" + fileName);
				} else {
					InputStream in = null;
					if (!AbStrUtil.isEmpty(assetDir)) {
						in = context.getAssets().open(assetDir + "/" + fileName);
					} else {
						in = context.getAssets().open(fileName);
					}
					File outFile = new File(outDir + "/" + fileName);
					if (outFile.exists()) {
						outFile.delete();
					}
					outFile.createNewFile();
					OutputStream out = new FileOutputStream(outFile);
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}

					in.close();
					out.close();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 描述：SD卡是否能用.
	 *
	 * @return true 可用,false不可用
	 */
	public static boolean isCanUseSD() {
		try {
			return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 描述：初始化存储目录.
	 *
	 * @param context
	 *            the context
	 */
	public static void initFileDir(Context context) {

		PackageInfo info = AbAppUtil.getPackageInfo(context);

		// 默认下载文件根目录.
		String downloadRootPath = File.separator + AbAppConfig.DOWNLOAD_ROOT_DIR + File.separator + info.packageName
				+ File.separator;

		// 默认下载图片文件目录.
		String imageDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_IMAGE_DIR + File.separator;

		// 默认下载文件目录.
		String fileDownloadPath = downloadRootPath + AbAppConfig.DOWNLOAD_FILE_DIR + File.separator;

		// 默认缓存目录.
		String cacheDownloadPath = downloadRootPath + AbAppConfig.CACHE_DIR + File.separator;

		// 默认DB目录.
		String dbDownloadPath = downloadRootPath + AbAppConfig.DB_DIR + File.separator;

		try {
			if (!isCanUseSD()) {
				return;
			} else {

				File root = Environment.getExternalStorageDirectory();
				File downloadDir = new File(root.getAbsolutePath() + downloadRootPath);
				if (!downloadDir.exists()) {
					downloadDir.mkdirs();
				}
				downloadRootDir = downloadDir.getPath();

				File cacheDownloadDirFile = new File(root.getAbsolutePath() + cacheDownloadPath);
				if (!cacheDownloadDirFile.exists()) {
					cacheDownloadDirFile.mkdirs();
				}
				cacheDownloadDir = cacheDownloadDirFile.getPath();

				File imageDownloadDirFile = new File(root.getAbsolutePath() + imageDownloadPath);
				if (!imageDownloadDirFile.exists()) {
					imageDownloadDirFile.mkdirs();
				}
				imageDownloadDir = imageDownloadDirFile.getPath();

				File fileDownloadDirFile = new File(root.getAbsolutePath() + fileDownloadPath);
				if (!fileDownloadDirFile.exists()) {
					fileDownloadDirFile.mkdirs();
				}
				fileDownloadDir = fileDownloadDirFile.getPath();

				File dbDownloadDirFile = new File(root.getAbsolutePath() + dbDownloadPath);
				if (!dbDownloadDirFile.exists()) {
					dbDownloadDirFile.mkdirs();
				}
				dbDownloadDir = dbDownloadDirFile.getPath();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算sdcard上的剩余空间.
	 *
	 * @return the int
	 */
	public static int freeSpaceOnSD() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / 1024 * 1024;
		return (int) sdFreeMB;
	}

	/**
	 * 根据文件的最后修改时间进行排序.
	 */
	public static class FileLastModifSort implements Comparator<File> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(File arg0, File arg1) {
			if (arg0.lastModified() > arg1.lastModified()) {
				return 1;
			} else if (arg0.lastModified() == arg1.lastModified()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	/**
	 * 删除所有缓存文件.
	 *
	 * @return true, if successful
	 */
	public static boolean clearDownloadFile() {
		try {
			File fileDirectory = new File(downloadRootDir);
			deleteFile(fileDirectory);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除文件.
	 *
	 * @return true, if successful
	 */
	public static boolean deleteFile(File file) {

		try {
			if (!isCanUseSD()) {
				return false;
			}
			if (file == null) {
				return true;
			}
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			} else {
				file.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 描述：读取Assets目录的文件内容.
	 *
	 * @param context
	 *            the context
	 * @param name
	 *            the name
	 * @param encoding
	 *            the encoding
	 * @return the string
	 */
	public static String readAssetsByName(Context context, String name, String encoding) {
		String text = null;
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		try {
			inputReader = new InputStreamReader(context.getAssets().open(name));
			bufReader = new BufferedReader(inputReader);
			String line = null;
			StringBuffer buffer = new StringBuffer();
			while ((line = bufReader.readLine()) != null) {
				buffer.append(line);
			}
			text = new String(buffer.toString().getBytes(), encoding);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return text;
	}

	/**
	 * 描述：读取Raw目录的文件内容.
	 *
	 * @param context
	 *            the context
	 * @param id
	 *            the id
	 * @param encoding
	 *            the encoding
	 * @return the string
	 */
	public static String readRawByName(Context context, int id, String encoding) {
		String text = null;
		InputStreamReader inputReader = null;
		BufferedReader bufReader = null;
		try {
			inputReader = new InputStreamReader(context.getResources().openRawResource(id));
			bufReader = new BufferedReader(inputReader);
			String line = null;
			StringBuffer buffer = new StringBuffer();
			while ((line = bufReader.readLine()) != null) {
				buffer.append(line);
			}
			text = new String(buffer.toString().getBytes(), encoding);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return text;
	}

	/**
	 * Gets the download root dir.
	 *
	 * @param context
	 *            the context
	 * @return the download root dir
	 */
	public static String getDownloadRootDir(Context context) {
		if (downloadRootDir == null) {
			initFileDir(context);
		}
		return downloadRootDir;
	}

	/**
	 * Gets the image download dir.
	 *
	 * @param context
	 *            the context
	 * @return the image download dir
	 */
	public static String getImageDownloadDir(Context context) {
		if (downloadRootDir == null) {
			initFileDir(context);
		}
		return imageDownloadDir;
	}

	/**
	 * Gets the file download dir.
	 *
	 * @param context
	 *            the context
	 * @return the file download dir
	 */
	public static String getFileDownloadDir(Context context) {
		if (downloadRootDir == null) {
			initFileDir(context);
		}
		return fileDownloadDir;
	}

	/**
	 * Gets the cache download dir.
	 *
	 * @param context
	 *            the context
	 * @return the cache download dir
	 */
	public static String getCacheDownloadDir(Context context) {
		if (downloadRootDir == null) {
			initFileDir(context);
		}
		return cacheDownloadDir;
	}

	/**
	 * Gets the db download dir.
	 *
	 * @param context
	 *            the context
	 * @return the db download dir
	 */
	public static String getDbDownloadDir(Context context) {
		if (downloadRootDir == null) {
			initFileDir(context);
		}
		return dbDownloadDir;
	}

	/**
	 * Gets the free sd space needed to cache.
	 *
	 * @return the free sd space needed to cache
	 */
	public static int getFreeSdSpaceNeededToCache() {
		return freeSdSpaceNeededToCache;
	}

}
