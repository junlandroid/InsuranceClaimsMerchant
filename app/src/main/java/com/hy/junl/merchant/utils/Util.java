package com.hy.junl.merchant.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;


import com.hy.junl.merchant.app.AppApplication;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by Ben on 2015/9/1.
 */
public class Util {
    public static AppApplication app;
    public static String md5(String data) {
        return new String(Hex.encodeHex(DigestUtils.md5(data)));
    }
    /*
    @Deprecated
    public static void urlToImageView(String _url, ImageView iv, ICActivity activity) {
        urlToImageView(_url, iv, activity, 1);
    }
    static Map<String, Bitmap> cache = new HashMap<>();
    public static int TARGET_URI = 0x19930120;
    public static int DEFAULT_IN_SAMPLE_SIZE = 4;
    @Deprecated
    public static void urlToImageView(String _url, ImageView iv, ICActivity activity, int inSampleSize) {
        String hash = md5(_url + "##" + inSampleSize);
        Bitmap cached_bm = cache.get(hash);
        if (cached_bm != null) {
            activity.handler.post(() -> {
                iv.setImageBitmap(cached_bm);
            });
        } else {
            new Thread(() -> {
                try {
                    URL url = new URL(_url);
                    Options options = new Options();
                    options.inSampleSize = inSampleSize;
                    Bitmap bm = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
                    cache.put(hash, bm);
                    activity.handler.post(() -> {
                        String target_uri = (String) iv.getTag(TARGET_URI);
                        if (target_uri == null || target_uri.equals(_url)) {
                            iv.setImageBitmap(bm);
                        }
                    });
                } catch (MalformedURLException e) {
                    // local file
                    try {
                        activity.handler.post(() -> iv.setImageURI(Uri.parse(_url)));
                    } catch (Exception e2) {
                        // e2.printStackTrace();
                        activity.handler.post(() -> app.pop(activity, R.string.PhotoView_error));
                    }

                } catch (IOException e) {
                    // e.printStackTrace();
                    activity.handler.post(() -> app.pop(activity, R.string.Global_network_error));
                }
            }).start();
        }
    }
    */
    public final static int UPLOAD_PHOTO_MAX_WIDTH = 1696;
    public final static int UPLOAD_PHOTO_PREVIEW_WIDTH = 256;
    public final static int UPLOAD_PHOTO_QUALITY = 90;
//    public static void remoteToImageView(String _url, ImageView iv, ICActivity activity, int max_size) {
//        String hash = md5(_url);
//        String cacheDir = Util.getExternalStorageRoot() + "/" + app.getString(R.string.app_name) + "/ImageCache/";
//        Log.e("123","ImageCache===>>>"+cacheDir);
//        final File dir = new File(cacheDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        String localCache = cacheDir + hash  + ".jpg";
//
//        try {
//            localToImageView(Uri.parse("file://" + localCache), iv, activity, max_size);
//        } catch (Exception e1) {
//            // 本地没有缓存
//            try {
//                URL url = new URL(_url);
//                new Thread(() -> {
//                    try {
//                        InputStream in = url.openConnection().getInputStream();
//                        File file = new File(localCache + ".downloading");
//                        if (!file.exists()) {
//                            file.createNewFile();
//                        }
//                        OutputStream fileCache = new FileOutputStream(file);
//
//                        byte[] buf = new byte[1024];
//                        int len;
//                        while((len = in.read(buf)) > 0){
//                            fileCache.write(buf, 0, len);
//                        }
//                        fileCache.close();
//                        in.close();
//
//                        // rename
//                        new File(localCache + ".downloading").renameTo(new File(localCache));
//
//                        localToImageView(Uri.parse("file://" + localCache), iv, activity, max_size);
//
//                    } catch (Exception e2) {
//                        // e2.printStackTrace();
//                        activity.handler.post(() -> app.pop(activity, R.string.Global_network_error));
//                    }
//                }).start();
//            } catch (Exception e3) {
//                // e3.printStackTrace();
//                activity.handler.post(() -> app.pop(activity, R.string.Global_system_error));
//            }
//        }
//
//    }

//    public static void localToImageView(Uri uri, ImageView iv, ICActivity activity, int max_size) throws NullPointerException {
//        try {
//            Bitmap bm = getLocalScaledBitmap(uri, max_size);
//            activity.handler.post(() -> iv.setImageBitmap(bm));
//
//        } catch (Exception e) {
//            // e.printStackTrace();
////             activity.handler.post(() -> app.pop(activity, R.string.Global_system_error));
//            throw new NullPointerException();
//        }
//    }

    public static Bitmap getLocalScaledBitmap(Uri uri) throws NullPointerException {
        return getLocalScaledBitmap(uri, UPLOAD_PHOTO_MAX_WIDTH);
    }

    public static Bitmap getLocalScaledBitmap(Uri uri, int max_size) throws NullPointerException {
        String url = uri.toString();
        Boolean isSdcardFile = url.startsWith("file://");   //检测字符串是否以里一个字符串开始，返回值类型是boolean类型
        if (isSdcardFile) {
            // 尝试从缓存中拿
            url += "@" + max_size;
            try {
                return BitmapFactory.decodeStream(AppApplication.getAppContext().getContentResolver().openInputStream(Uri.parse(url)));
            } catch (Exception e) {
                // do nothing
            }
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            /**
             * inJustDecodeBounds设置为true，是禁止为bitmap的像素分配内存，bitmap返回值为空，但次方法可以计算出原始图片长度和宽度
             */
            options.inJustDecodeBounds = true;//可以不把图片读到内存中,但依然可以计算出图片的大小，
            Bitmap bm = BitmapFactory.decodeStream(AppApplication.getAppContext().getContentResolver().openInputStream(uri), null, options);

            //获取图片的尺寸
            int width = options.outWidth;   //图片的宽度
            int height = options.outHeight; //图片的高度
            int srcSize = Math.max(width, height);  //该方法返回两参数中最大的一个值
            int scale = srcSize / UPLOAD_PHOTO_MAX_WIDTH;
            if (scale < 1) {
                scale = 1;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false; // 使用计算得到的inSampleSize值再次解析图片
            bm = BitmapFactory.decodeStream(AppApplication.getAppContext().getContentResolver().openInputStream(uri), null, options);
            if (srcSize <= max_size) {
                return bm;
            } else {
                float scale2 = srcSize <= max_size ? 1.0f : (float) max_size / srcSize;
                width *= scale2;
                height *= scale2;
                Bitmap scaled = Bitmap.createScaledBitmap(bm, width, height, true);

                Log.i("123","Bitmap===>>>width:  "+width+"===height:  "+height);

                if (isSdcardFile) {
                    /*
                     *  url 可能有中文，要解码…
                     */
                    FileOutputStream out = new FileOutputStream(java.net.URLDecoder.decode(url.substring(7), "utf-8"));
                    scaled.compress(Bitmap.CompressFormat.JPEG, UPLOAD_PHOTO_QUALITY, out);
                    out.flush();
                    out.close();
                }
                return scaled;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }
    /**
     * 屏幕宽高
     *
     * @param activity 类对象
     * @return
     */
    private static float[] getScreenSize(Activity activity) {
        // 获取屏幕分辨率
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float screenWidth = dm.widthPixels;
        float screenHeight = dm.heightPixels;
        return new float[]{screenWidth, screenHeight};
    }



    public static String getExternalStorageRoot() {
        freshExternalStorageStatus();
        return mExternalStorageRoot;
    }
    public static boolean getExternalStorageWriteable() {
        freshExternalStorageStatus();
        return mExternalStorageWriteable;
    }

    private static boolean mExternalStorageAvailable = false;
    private static boolean mExternalStorageWriteable = false;
    private static String mExternalStorageRoot = "";
    private static void freshExternalStorageStatus() {
        String state = Environment.getExternalStorageState();
        mExternalStorageRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (Environment.MEDIA_MOUNTED.equals(state)) {  //判断SDcard正常挂载
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) { //SDcard存在并且已挂载，但是挂载方式为只读
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
    }
//    public static void enable_btn(Button btn) {
//        Thread thread = new Thread(() -> {
//            try {
//                int count = 60;
//                String btn_text = btn.getText().toString();
//                while (count > 0) {
//                    final int j = count;
//                    btn.getHandler().post(() -> {
//                        btn.setText("" + j + app.getString(R.string.Global_second));
//                    });
//                    Thread.sleep(1000);
//                    count -= 1;
//                }
//                btn.getHandler().post(() -> {
//                    btn.setEnabled(true);
//                    btn.setText(btn_text);
//                });
//            } catch (Exception e){
//                // do nothing
//            }
//        });
//        thread.start();
//    }
//    public static String getMac() {
//        try {
//            WifiManager manager = (WifiManager) app.getSystemService(Context.WIFI_SERVICE);
//            WifiInfo info = manager.getConnectionInfo();
//            return info.getMacAddress();
//        } catch (Exception e) {
//            return Settings.Secure.getString(app.getContentResolver(),
//                    Settings.Secure.ANDROID_ID);
//        }
//    }

    /**
     * 获取手机的MAC地址
     * @return Mac地址
     */
    public static String getMac() {
        String macSerial = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            String line;
            while ((line = input.readLine()) != null) {
                macSerial += line.trim();
            }

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macSerial;
    }
    public static String getClientInfo() {
        return android.os.Build.MODEL + "(" + android.os.Build.VERSION.RELEASE +")";
    }

}
