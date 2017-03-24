package com.olimou.android.circle_avatar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * Created by EmersonMoura on 28/09/16.
 */

public class AvatarView extends FrameLayout {

	public static final String TAG = AvatarView.class.getSimpleName();

	private SimpleDraweeView mCircularImageView;
	private TextView         mTextView;

	public AvatarView(Context context) {
		super(context);

		init(null);
	}

	public AvatarView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(attrs);
	}

	public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init(attrs);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public AvatarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);

		init(attrs);
	}

	public static void setLetter(AvatarView _letter, String _name) {
		_letter.setName(_name);
	}

	public SimpleDraweeView getCircularImageView() {
		return mCircularImageView;
	}

	public int getColor(int _colorResource) {
		int[] lInts = {R.color.light_blue_500,
				R.color.purple_500,
				R.color.green_500,
				R.color.red_500,
				R.color.orange_500,
				R.color.blue_500,
				R.color.pink_500,
				R.color.indigo_500,
				R.color.deep_orange_500,
				R.color.amber_500};

		return lInts[_colorResource];
	}

	public TextView getTextView() {
		return mTextView;
	}

	public void init(@Nullable AttributeSet _attrs) {
		View lView = inflate(getContext(), R.layout.component_avatar_view, this);

		mCircularImageView = (SimpleDraweeView) lView.findViewById(R.id.circular_imageview);

		mTextView = (TextView) lView.findViewById(R.id.textview);

		mTextView.setVisibility(VISIBLE);

		if (_attrs != null) {
			TypedArray a = getContext().getTheme()
					.obtainStyledAttributes(_attrs, R.styleable.AvatarView, 0, 0);
			try {
				String lString = a.getString(R.styleable.AvatarView_text);

				if (lString != null) {
					setName(lString);
				}

				int lRandomColor = a.getInt(R.styleable.AvatarView_randomColor, -1);

				if (lRandomColor > 0) {
					setColorIndex(lRandomColor);
				} else {
					int lColor = a.getColor(R.styleable.AvatarView_color,
							getContext().getResources().getColor(R.color.grey_300));
				}
			} finally {
				a.recycle();
			}
		}
	}

	public void setAvatarUrl(String _avatarUrl) {
		PipelineDraweeControllerBuilder lControllerBuilder = Fresco.newDraweeControllerBuilder();

		ControllerListener<ImageInfo> lControllerListener = new ControllerListener<ImageInfo>() {
			@Override
			public void onFailure(String id, Throwable throwable) {

			}

			@Override
			public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
				mTextView.setVisibility(GONE);
			}

			@Override
			public void onIntermediateImageFailed(String id, Throwable throwable) {
			}

			@Override
			public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
			}

			@Override
			public void onRelease(String id) {
			}

			@Override
			public void onSubmit(String id, Object callerContext) {
			}
		};

		lControllerBuilder.setControllerListener(lControllerListener);
		lControllerBuilder.setUri(_avatarUrl);

		mTextView.setVisibility(VISIBLE);

		mCircularImageView.setController(lControllerBuilder.build());
	}

	public void setBackgroundColor(int _randomColor) {
		try {
			mCircularImageView.getHierarchy().setPlaceholderImage(_randomColor);
		} catch (Exception _backgroundColor) {
			_backgroundColor.printStackTrace();
		}
	}

	public void setColorIndex(int _index) {
		while (_index >= 10) {
			_index = _index - 10;
		}

		setBackgroundColor(getColor(_index));
	}

	public void setName(String _name) {
		GenerateLetters lGenerateLetters = new GenerateLetters();

		mTextView.setText(lGenerateLetters.generate(_name));
	}
}
