package com.xi.liuliu.tts.util;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * Created by Administrator on 2020/4/17.
 */

public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();
    private static final int BUFFER_SIZE = 4096;

    public static File createNewFile(File file) {
        try {
            if (file.exists()) {
                return file;
            }
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public static File createNewFile(String path) {
        File file = new File(path);
        return createNewFile(file);
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        deleteFile(file);
    }

    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
        file.delete();
    }

    public static boolean createDir(File dir) {
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static File createDir(String dirName) {
        File dir = new File(dirName);
        dir.mkdir();
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static String getFileContent(String filename) {
        ByteArrayOutputStream outputStream = null;
        FileInputStream fis = null;
        try {
            outputStream = new ByteArrayOutputStream();
            File file = new File(filename);
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                fis = new FileInputStream(file);
                int len = 0;
                byte[] data = new byte[BUFFER_SIZE];
                while ((len = fis.read(data)) != -1) {
                    outputStream.write(data, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String(outputStream.toByteArray());
    }

    private static long getFileSize(File f) {
        long size = 0;
        try {
            if (f.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(f);
                size = fis.available();
            } else {
                f.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


    public static void getAllFiles(String dirPath, List<String> list) {
        File f = new File(dirPath);
        if (!f.exists()) {
            return;
        }
        File[] files = f.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                String filePath = file.getAbsolutePath();
                list.add(filePath);
            } else if (file.isDirectory()) {
                getAllFiles(file.getAbsolutePath(), list);
            }
        }
    }

    public static void getAllAudioFiles(String dirPath, List<String> list) {
        File f = new File(dirPath);
        if (!f.exists()) {
            return;
        }
        File[] files = f.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile() && (file.getName().trim().endsWith(".pcm") || file.getName().trim().endsWith(".wav"))) {
                String filePath = file.getAbsolutePath();
                list.add(filePath);
            } else if (file.isDirectory()) {
                getAllAudioFiles(file.getAbsolutePath(), list);
            }
        }
    }

    public static void writeStr2File(String content, String filePath, String fileName) {
        String strFilePath = filePath + fileName;
        File file = createNewFile(strFilePath);
        String strContent = content;
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File writeInputStream2File(String path, String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            createDir(path);
            file = createNewFile(path + fileName);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[BUFFER_SIZE];
            int len = -1;
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
