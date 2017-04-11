package com.zhangwx.dynamicpermissionsrequest.permission.bridge;


/**
 * Created by zhangwx on 2017/4/5.
 */

public interface IPermissionRequest {
    void request(int requestCode,
                 String[] permissionGroup,
                 String rationale,
                 PermissionRequestBridge.RequestCallBack callBack);
}
