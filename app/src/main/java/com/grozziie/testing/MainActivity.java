package com.grozziie.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView ddddd=findViewById(R.id.ddddd);
        LinearLayout verticalContainer = findViewById(R.id.verticalContainer);
        String text = "Your text here";
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            TextView textView = new TextView(this);
            textView.setText(String.valueOf(character));
            textView.setTextSize(16); // Adjust the text size as needed
            textView.setTextColor(Color.BLACK); // Adjust the text color as needed
            verticalContainer.addView(textView);
        }
        for (int i = 0; i < verticalContainer.getChildCount(); i++) {
            View child = verticalContainer.getChildAt(i);
            child.setRotation(90);
        }
        verticalContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        verticalContainer.requestLayout();

        //
        FrameLayout container = findViewById(R.id.container);
        container.setOnTouchListener(new MyTouchListener());
        MyDragListener dragListener = new MyDragListener();
        container.setOnDragListener(dragListener);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        // Create an EditText dynamically
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(editTextParams);
        editText.setHint("Enter text here");

        // Add the EditText to the root LinearLayout
        rootLayout.addView(editText);

        // Set the root LinearLayout as the content view
        setContentView(rootLayout);

        //
        LinearLayout rootLayout1 = new LinearLayout(this);
        rootLayout1.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout1.setOrientation(LinearLayout.VERTICAL);

        // Create an ImageView dynamically
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageLayoutParams);
        imageView.setImageResource(R.drawable.ic_launcher_background); // Set your desired image resource

        // Add the ImageView to the root LinearLayout
        rootLayout1.addView(imageView);

        // Set the root LinearLayout as the content view
        setContentView(rootLayout1);
        imageView.setOnTouchListener(new MyTouchListener());
        MyDragListener dragListener1 = new MyDragListener();
        imageView.setOnDragListener(dragListener1);
    }
    private class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Start drag operation
                    ClipData clipData = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(clipData, shadowBuilder, view, 0);
                    return true;
            }
            return false;
        }
    }

    private class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    // Handle drop event
                    View draggedView = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) draggedView.getParent();
                    owner.removeView(draggedView);
                    FrameLayout container = (FrameLayout) view;
                    container.addView(draggedView);
                    draggedView.setVisibility(View.VISIBLE);
                    return true;
            }
            return true;
        }
    }

}