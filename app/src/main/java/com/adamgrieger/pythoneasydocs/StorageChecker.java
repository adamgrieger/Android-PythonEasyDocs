package com.adamgrieger.pythoneasydocs;

import android.os.Environment;


/**
 * Class that contains methods for checking external storage reading and writing access.
 */
public class StorageChecker {

    /**
     * Checks if external storage is available for read and write.
     *
     * @return true if available, false if otherwise
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if external storage is available to at least read.
     *
     * @return true if available, false if otherwise
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        } else {
            return false;
        }
    }
}
