package com.aline.mydemo.activity.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MuPdfActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TEST_FILE_NAME = "sample.pdf";

    public String getDirectoryPathById(final Integer recordId) {
        if (recordId == null)
            return "";

        final String fullRecordId = String.format("%010d", recordId);
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i <= fullRecordId.length() - 2; i = i + 2)
            result.append(fullRecordId.substring(i, i + 2)).append(File.separator);

        return result.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mu_pdf);

        File fo = getFilesDirectory(getApplicationContext());
        File fi = new File(fo, TEST_FILE_NAME);


        if (!fi.exists()) {
            copyTestDocToSdCard(fi);
        }

        Button bt = (Button)findViewById(R.id.button1);
        bt.setOnClickListener(this);

        Log.e("asadasdadsa", "59057: "+getDirectoryPathById(59057)+", 58492: "+getDirectoryPathById(58492));
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.button1)) {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            sharedPrefs.edit().putString("prefKeyLanguage", "en").commit();

            File fo = getFilesDirectory(getApplicationContext());
            File fi = new File(fo, TEST_FILE_NAME);
            Uri uri = Uri.parse(fi.getAbsolutePath());

            //Uri uri = Uri.parse("file:///android_asset/" + TEST_FILE_NAME);
//            Intent intent = new Intent(this, com.artifex.mupdflib.MuPDFActivity.class);
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.setData(uri);

            //if document protected with password
//            intent.putExtra("password", "encrypted PDF password");
//
//            //if you need highlight link boxes
//            intent.putExtra("linkhighlight", true);
//
//            //if you don't need device sleep on reading document
//            intent.putExtra("idleenabled", false);
//
//            //set true value for horizontal page scrolling, false value for vertical page scrolling
//            intent.putExtra("horizontalscrolling", true);
//
//            //document name
//            intent.putExtra("docname", "PDF document name");
//
//            startActivity(intent);
        }
    }

    private void copyTestDocToSdCard(final File testImageOnSdCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = getAssets().open(TEST_FILE_NAME);
                    FileOutputStream fos = new FileOutputStream(testImageOnSdCard);
                    byte[] buffer = new byte[8192];
                    int read;
                    try {
                        while ((read = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, read);
                        }
                    } finally {
                        fos.flush();
                        fos.close();
                        is.close();
                    }
                } catch (IOException e) {

                }
            }
        }).start();
    }

    public static File getFilesDirectory(Context context) {
        File appFilesDir = null;
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            appFilesDir = getExternalFilesDir(context);
        }
        if (appFilesDir == null) {
            appFilesDir = context.getFilesDir();
        }
        return appFilesDir;
    }

    private static File getExternalFilesDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appFilesDir = new File(new File(dataDir, context.getPackageName()), "files");
        if (!appFilesDir.exists()) {
            if (!appFilesDir.mkdirs()) {
                //L.w("Unable to create external cache directory");
                return null;
            }
            try {
                new File(appFilesDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                //L.i("Can't create \".nomedia\" file in application external cache directory");
                return null;
            }
        }
        return appFilesDir;
    }
}