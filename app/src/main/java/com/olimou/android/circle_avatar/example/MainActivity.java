package com.olimou.android.circle_avatar.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.olimou.android.example.AvatarView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Fresco.initialize(this);

		setContentView(R.layout.activity_main);

		LinearLayout lLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);

		int lDimension = (int) getResources().getDimension(R.dimen.avatar_size);

		for (int i = 0; i < 15; i++) {
			AvatarView lAvatarView = new AvatarView(this);

			LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(lDimension,
					lDimension);

			lLayoutParams.setMargins(40, 40, 40, 40);

			lAvatarView.setLayoutParams(lLayoutParams);

			lAvatarView.invalidate();
			lAvatarView.setName(String.format(Locale.getDefault(), "A%d", i));
			lAvatarView.setColorIndex(i);
			lLinearLayout.addView(lAvatarView);
		}
	}
}
