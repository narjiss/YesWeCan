package com.dl.android.pelotedelaine;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PictureActivity extends Activity {
	private static final int TAKE_PICTURE = 0;
	final int CAPTURE_IMAGE = 1;
	public Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);

		/**
		 * Prendre une photo et la stocker dans un fichier:
		 */
		takePhoto();
	}

	public void takePhoto() {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		File photo = new File(Environment.getExternalStorageDirectory(),
				"Pic.jpg");

		imageUri = Uri.fromFile(photo);
		
		intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
		
		Toast.makeText(this, photo.toString(),Toast.LENGTH_LONG).show();
		startActivityForResult(intent, TAKE_PICTURE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_PICTURE:
			if (resultCode == Activity.RESULT_OK) {
			
//				Uri selectedImage = Uri.fromFile(new File("/mnt/emmc/DCIM/100MEDIA/IMAG0014.JPG"));
				Uri selectedImage = imageUri;
				Log.v(" image URI Result", imageUri.toString());
				
				getContentResolver().notifyChange(selectedImage, null);
				ImageView imageView = (ImageView) findViewById(R.id.ImageView);
				ContentResolver cr = getContentResolver();
				Bitmap bitmap;
				try {
					bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
//					Bitmap bitmap2 = bitmap.copy(bitmap.getConfig(), true);
					imageView.setImageBitmap(bitmap);
					Toast.makeText(this, selectedImage.toString(),Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
							.show();
					Log.e("Camera", e.toString());
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
