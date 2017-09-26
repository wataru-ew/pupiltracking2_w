package com.wataru.pupiltracking2_w;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import org.opencv.core.Point;

import java.util.ArrayList;

/**
 * Created by wataru on 2017/09/23.
 */

public class onDrawView extends View {
    double x, y, r;
    int work;
    ArrayList<Point> eye = new ArrayList<Point>();
    Point p1, p2;

    public void tran(double x, double y, int r, int work) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.work = work;
    }

    public void tran2(ArrayList<Point> eye, int r, int work) {
        this.eye = eye;
        this.r = r;
        this.work = work;
    }
    public onDrawView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(10);

        Paint rec = new Paint();
        rec.setColor(Color.RED);
        switch (work) {
            case 1:
                canvas.drawCircle((float) x, (float) y, (float) r, rec);
                break;
            case 2:
                p1 = eye.get(0);
                for (int a = 0; a < (eye.size() - 1); a++) {
                    p2 = eye.get(a + 1);
                    if (p2.x - p1.x + p2.y - p1.y < 200) {
                        p2.x = (p2.x - p1.x) / 3 + p1.x;
                        p2.y = (p2.y - p1.y) / 3 + p1.y;
                    } else {
                        if (p2.x - p1.x + p2.y - p1.y < 300) {
                            p2.x = (p2.x - p1.x) / 2 + p1.x;
                            p2.y = (p2.y - p1.y) / 2 + p1.y;
                        }
                    }
                    canvas.drawCircle((float) p1.x, (float) p1.y, (float) r, rec);
                    canvas.drawLine((float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, p);
                    p1 = p2;
                }
                canvas.drawCircle((float) p2.x, (float) p2.y, (float) r, rec);
        }

    }
}
