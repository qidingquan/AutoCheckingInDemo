package com.spring.autocheckingindemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/7/27.
 * 网络广播监听
 */

public class NetBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "NetBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 这个监听网络连接的设置，包括wifi和移动数据的打开和关闭。.
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {

            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.isConnected()) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        Log.e(TAG, "当前WiFi连接可用 ");
                        startOtherApp(context,"com.alibaba.android.rimet");
                        AppManager.getAppManager().AppExit(context,false);
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        Log.e(TAG, "当前移动网络连接可用 ");
                    }
                } else {
                    Log.e(TAG, "当前没有网络连接，请确保你已经打开网络 ");
                }
            } else {   // not connected to the internet
                Log.e(TAG, "当前没有网络连接，请确保你已经打开网络 ");
            }


        }
    }

    /**
     * 启动其他app应用
     * @param context
     * @param packageName 应用包名
     */
    public void startOtherApp(Context context,String packageName) {

        PackageManager packageManager = context.getPackageManager();
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        } else {
            Toast.makeText(context, "手机未安装该应用", Toast.LENGTH_SHORT).show();
        }
    }

}
