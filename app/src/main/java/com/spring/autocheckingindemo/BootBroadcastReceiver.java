package com.spring.autocheckingindemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/7/27.
 * 当手机开机时接收广播监听
 * 开机后自动开启应用
 */

public class BootBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"自启动程序即将执行",Toast.LENGTH_SHORT).show();
        //MainActivity就是开机显示的界面
        Intent mBootIntent = new Intent(context, MainActivity.class);
        //下面这句话必须加上才能开机自动运行app的界面
        mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mBootIntent);
    }

}
