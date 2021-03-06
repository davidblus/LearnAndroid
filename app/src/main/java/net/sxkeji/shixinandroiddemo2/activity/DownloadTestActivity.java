package net.sxkeji.shixinandroiddemo2.activity;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import net.sxkeji.shixinandroiddemo2.BaseActivity;
import net.sxkeji.shixinandroiddemo2.R;
import net.sxkeji.shixinandroiddemo2.annotation.AutoRegister;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.shixinzhang.sxframework.network.download.IAPKDownloader;
import top.shixinzhang.sxframework.network.download.imp.DefaultDownloader;
import top.shixinzhang.sxframework.manager.update.APKDownloader;
import top.shixinzhang.sxframework.manager.update.model.UpdateResponseInfo;
import top.shixinzhang.utils.ApplicationUtils;
import top.shixinzhang.utils.DateUtils;

/**
 * Description:
 * <br>
 * <p>
 * <br> Created by shixinzhang on 17/4/27.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <a  href="https://about.me/shixinzhang">About me</a>
 */

@AutoRegister(label = "测试下载")
public class DownloadTestActivity extends BaseActivity {
    @BindView(R.id.tv_app_info)
    TextView mTvAppInfo;

    private String TEST_APK_URL2 = "http://www.wandoujia.com/apps/com.dianping.v1/binding?source=wandoujia-web_direct_binded";
//    private String TEST_APK_URL = "http://shixinoss.oss-cn-shanghai.aliyuncs.com/apk/shixin_wandoujia_release_6.0_2017-04-28.apk";
    private String TEST_BIG_FILE_URL = "http://shixinoss.oss-cn-shanghai.aliyuncs.com/install/%E9%B8%9F%E5%93%A5%E7%9A%84Linux%E7%A7%81%E6%88%BF%E8%8F%9C%E5%9F%BA%E7%A1%80%E7%AF%87%E7%AC%AC%E4%B8%89%E7%89%88.pdf";
    private String TEST_IMG_URL = "http://shixinoss.oss-cn-shanghai.aliyuncs.com/image/%E6%B8%85%E6%99%A8.jpeg";

    private IAPKDownloader mApkDownloader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        loadData();
    }

    @OnClick(R.id.btn_check_update)
    public void checkUpdate() {

        UpdateResponseInfo updateInfo = new UpdateResponseInfo.Builder()
                .needUpdate(true)
                .appName("shixinzhangApp")
                .appVersion("1.2.0")
                .downType(1)
                .forceUpdate(false)
                .silentDownload(true)
                .downloadUrl(TEST_APK_URL2)
                .build();


    }

    @OnClick(R.id.btn_test_auto_install)
    public void autoInstall() {
//        try {
//            ApplicationUtils.autoInstallApp("shixinzhang.apk");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @OnClick(R.id.btn_download)
    public void download() {
        new APKDownloader.Builder()
                .context(this)
                .apkName("shixinzhang.apk")
                .title("shixinzhang app")
                .url(TEST_APK_URL2)
                .build()
                .download();
    }

    @OnClick(R.id.btn_download_big_file)
    public void downloadBigFile() {
        mApkDownloader
                .setUrl(TEST_BIG_FILE_URL)
                .setNotificationTitle("pdf download")
                .setNotificationDesc("shixinzhang 正在下载中...")
                .setFileName("test.pdf")
                .prepare()
                .download(null, null);
    }

    @OnClick(R.id.btn_download_image)
    public void downloadImage() {
        mApkDownloader
                .setUrl(TEST_IMG_URL)
                .setNotificationTitle("Image")
                .setNotificationDesc("shixinzhang 正在下载中...")
                .setFileName("test.png")
                .prepare()
                .download(null, null);
    }


    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
        PackageInfo packageInfo = ApplicationUtils.getCurrentAppInfo(this);
        if (packageInfo != null) {
            mTvAppInfo.setText(packageInfo.packageName + "\n versionName:" + packageInfo.versionName
                    + "\n versionCode:" + packageInfo.versionCode + "\n lastUpdateTime:" + DateUtils.getDateString(packageInfo.lastUpdateTime));
        }

        mApkDownloader = DefaultDownloader.getInstance(DownloadTestActivity.this);
    }

    public void addListeners() {

    }
}
