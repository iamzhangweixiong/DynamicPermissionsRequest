package com.zhangwx.dynamicpermissionsrequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zhangwx.dynamicpermissionsrequest.permission.AppSettingsDialog;
import com.zhangwx.dynamicpermissionsrequest.permission.EasyPermissions;
import com.zhangwx.dynamicpermissionsrequest.permission.PermissionUtils;

import java.util.List;

/**
 * Created by zhangwx on 2017/4/11.
 */

public class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //用户勾选了“不再询问”，以后每次都跳转到权限设置界面
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //从权限设置界面返回，再次判断是否有权限
            if (EasyPermissions.hasPermissions(this, PermissionUtils.PERMISSION_CAMERA_GROUP)) {
                doCameraThings();
            }
        }
    }

    protected void doCameraThings() {
    }
}
