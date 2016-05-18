package common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class OctagonLayout extends RelativeLayout {

	public OctagonLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public OctagonLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public OctagonLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Path clipPath = new Path();
		clipPath.addPath(Octagon());
		canvas.clipPath(clipPath);

		canvas.drawColor(Color.parseColor("#3C5AA0"));

		super.onDraw(canvas);
	}

	private Path Octagon() {

		Path p = new Path();
		float midX = getWidth() / 2.0f;
		float midY = getHeight() / 2.0f;
		p.moveTo(midX, midY);
		p.lineTo(midX + 30, midY + 10);
		p.lineTo(midX + 10, midY + 30);
		p.lineTo(midX - 10, midY + 30);
		p.lineTo(midX - 30, midY + 10);
		p.lineTo(midX - 30, midY - 10);
		p.lineTo(midX - 10, midY - 30);
		p.lineTo(midX + 10, midY - 30);
		p.lineTo(midX + 30, midY - 10);
		p.lineTo(midX + 30, midY + 10);

		return p;

	}

}
