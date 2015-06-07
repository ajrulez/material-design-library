package com.blunderer.materialdesignlibrary.sample.cardviews;

import android.view.View;
import android.widget.Toast;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.views.CardView;
import com.blunderer.materialdesignlibrary.sample.R;

public class CardViewWithLeftImageActivity
        extends com.blunderer.materialdesignlibrary.activities.Activity {

    @Override
    protected int getContentView() {
        return R.layout.activity_cardview_with_left_image;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

    @Override
    protected void onCreateComplete() {
        CardView cardView = (CardView) findViewById(R.id.cardview);

        if(cardView != null) {
            cardView.setOnHighlightButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CardViewWithLeftImageActivity.this, "Highlight Button clicked", Toast.LENGTH_SHORT).show();
                }
            });

            cardView.setOnNormalButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CardViewWithLeftImageActivity.this, "Normal Button clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
