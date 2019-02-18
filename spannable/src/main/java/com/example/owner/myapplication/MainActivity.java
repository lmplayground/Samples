package com.example.owner.myapplication;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {


    SpannableStringBuilder bulletSSBuilder;
    SpannableStringBuilder imageSSBuilder;
    String mText;
    TextView clickable;
    TextView bullet;
    TextView images;
    String clickable_text;
    String bullet_text;
    SpannableString clickableString;
    BulletSpan myBulletspan;
    SpannableString bulletString;
    String imageText;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.some_examples);

        final Button btnMore = findViewById(R.id.moreExamples);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnMore.getText() == "Back"){

                        MainActivity.super.onBackPressed();
                        btnMore.setText("More Examples");

                } else{
                MoreExamplesFragment fragment = new MoreExamplesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                btnMore.setText("Back");
                }

            }
        });


        //Clickable
        clickable = findViewById(R.id.clickable_txt);
        clickable_text = "I want THIS and THIS to be clickable";


        clickableString = new SpannableString(clickable_text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(MainActivity.this, "One", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);

            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(MainActivity.this, "Two", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);

            }
        };

        clickableString.setSpan(clickableSpan1, 7, 11, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        clickableString.setSpan(clickableSpan2, 16, 20, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        clickableString.setSpan(new ForegroundColorSpan(Color.GREEN), 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        clickable.setText(clickableString);
        clickable.setMovementMethod(LinkMovementMethod.getInstance());


//        The flag marks whether the span should expand to include text inserted at their starting or ending point, or not.
//                Independent of which flag is set, whenever text is inserted at a position greater than the starting point and less than the ending point,
//                the span will automatically expand.


        //Todo Bullet

        bullet = findViewById(R.id.bullet_txt);
        bullet_text = "My text with \nbullet one \nbullet two";

        ////1
////        myBulletspan = new BulletSpan(40, Color.BLACK, 20);
////        bulletString = new SpannableString(bullet_text);
////        bulletString.setSpan(new BulletSpan(), 14, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        bulletString.setSpan(new ForegroundColorSpan(Color.RED), 14, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        bulletString.setSpan(new BulletSpan(), 26, bullet_text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        bulletString.setSpan(new ForegroundColorSpan(Color.RED), 26, bullet_text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        bullet.setText(bulletString);

        //2

        mText = "Test text to display bulleted list." +
                " \nRed \nGreen \nBlue \nYellow";
        bulletSSBuilder = new SpannableStringBuilder(mText);

        // Generate bulleted list
        showBullet("Red");
        showBullet("Green");
        showBullet("Blue");
        showBullet("Yellow");

        // Display the spannable text to TextView
        bullet.setText(bulletSSBuilder);

        //Todo Image
        images = findViewById(R.id.image_txt);
        imageText = "Test text to show ImageSpan in a TextView. This is a search icon and" +
                " this is a play  icon";
        imageSSBuilder = new SpannableStringBuilder(imageText);
        ImageSpan searchImageSpan = new ImageSpan(getApplicationContext(), R.drawable.search_24);
        ImageSpan playImageSpan = new ImageSpan(getApplicationContext(), R.drawable.next_24);

        imageSSBuilder.setSpan(
                searchImageSpan, // Span to add
                imageText.indexOf("search") + String.valueOf("search").length(), // Start of the span (inclusive)
                imageText.indexOf("search") + String.valueOf("search").length() + 1, // End of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // Do not extend the span when text add later
        );


        imageSSBuilder.setSpan(
                playImageSpan,
                imageText.indexOf("play") + String.valueOf("play").length(),
                imageText.indexOf("play") + String.valueOf("play").length() + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        // Display the spannable text to TextView
        images.setText(imageSSBuilder);


        //Todo Html.fromHtml(text);

        TextView tv = (TextView) findViewById(R.id.from_html_text);
        tv.setText(Html.fromHtml(getString(R.string.my_text)));
        Log.i("HTML String",(Html.fromHtml(getString(R.string.my_text))).toString());// Log

        //Todo  Insert+StringBuilder

        SpannableStringBuilder builder= new SpannableStringBuilder();
        StyleSpan boldSpan = new StyleSpan(android.graphics.Typeface.BOLD);
        builder.append("First Part Not Bold ")
                .append("BOLD ", boldSpan, Spanned.SPAN_INCLUSIVE_EXCLUSIVE) // Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                .append("rest not bold");
        clickable.setText(builder);

        builder.insert(20, "Very ");
        clickable.setText(builder);



        //Todo ScrollMovement method
        clickable.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        clickable.setHeight(200);
        clickable.setMovementMethod(new ScrollingMovementMethod());

    };

    protected void showBullet(String textToBullet) {
        // Initialize a new BulletSpan
        BulletSpan bulletSpan = new BulletSpan(
                30, // Gap width
                Color.RED// Color for Bullet
                //20// Radius for Bullet
        );

        // Apply the bullet to the span
        bulletSSBuilder.setSpan(
                bulletSpan, // Span to add
                mText.indexOf(textToBullet), // Start of the span (inclusive)
                mText.indexOf(textToBullet) + 1,  // End of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // Do not extend the span when text add later
        );
    }
}
