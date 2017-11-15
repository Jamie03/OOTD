package com.example.pat4008.ootd;

import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.Environment;
        import android.os.StrictMode;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

        import java.io.File;

public class CaptureActivity extends AppCompatActivity {

    ImageButton buttonCamera;
    ImageView ivPhoto;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        buttonCamera = (ImageButton) findViewById(R.id.button_capture);
        ivPhoto = (ImageView) findViewById(R.id.iv_preview);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });

        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO lead the user to the activity to create a new record
                Intent i = new Intent();
                // Note: make sure to pass an extra that we want to add and not edit
                i.setClass(getBaseContext(), DescribeActivity.class);
                i.putExtra("id", "cam_image.jpg");
                startActivity(i);
            }
        });

    }

    private File getFile() {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/OOTD");

        if(!folder.exists()) {
            folder.mkdir();
        }

        File image_file = new File(folder, "/cam_image.jpg");

        if(!image_file.exists())
            image_file.delete();

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("myTag", "This is my message");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/OOTD/cam_image.jpg";
        ivPhoto.setImageDrawable(Drawable.createFromPath(path));
    }
}