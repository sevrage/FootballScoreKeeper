package com.example.android.footballscorekeeper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

class SingleToast {

    private static Toast mToast;

    public static void show(Context context, String text, String textColor) {
        reset();
        mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        View view = mToast.getView();
        view.setBackgroundResource(R.drawable.toast);
        TextView textToast = view.findViewById(android.R.id.message);
        textToast.setTextSize(22);
        textToast.setTextColor(Color.parseColor(textColor));
        textToast.setTypeface(Typeface.DEFAULT_BOLD);
        mToast.show();
    }

    private static void reset() {
        if (mToast != null) mToast.cancel();
    }


}
