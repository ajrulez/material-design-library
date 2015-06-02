package com.blunderer.materialdesignlibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.NavigationDrawerAdapter;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.interfaces.NavigationDrawer;
import com.blunderer.materialdesignlibrary.models.ListItem;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerViewPagerListItem;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.blunderer.materialdesignlibrary.views.ToolbarSearch;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Modifications by anujsaluja on 5/30/15.
 *
 * Original Source Code Credit: Denis Mondon
 * GitHub: https://github.com/DenisMondon/material-design-library
 */
public abstract class NavigationDrawerWithViewPagerActivity extends NavigationDrawerActivity
        implements com.blunderer.materialdesignlibrary.interfaces.ViewPager {

    protected ViewPager mViewPager;
    protected CirclePageIndicator mViewPagerIndicator;
    private List<ViewPagerItem> mViewPagerItems;
    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager
            .OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            replaceTitle(mViewPagerItems.get(position).getTitle());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ToolbarSearch.SEARCH_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        else if (mCurrentItem != null && mCurrentItem.getFragment() != null) {
            mCurrentItem.getFragment().onActivityResult(requestCode, resultCode, data);
        }
        else if (mViewPagerItems != null && mViewPagerItems.size() > 0 && mViewPager != null) {
            int tabPosition = mViewPager.getCurrentItem();
            if (tabPosition >= 0 && tabPosition < mViewPagerItems.size()) {
                mViewPagerItems.get(tabPosition).getFragment()
                        .onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.mdl_activity_navigation_drawer_view_pager);

        if (savedInstanceState != null) {
            mAccountsPositions = savedInstanceState.getIntArray("cc");
        }

        ViewPagerHandler handler = getViewPagerHandler();
        if (handler != null && handler.getViewPagerItems() != null) {
            mViewPagerItems = handler.getViewPagerItems();
        }

        if (mViewPagerItems != null && mViewPagerItems.size() > 0) {
            mViewPager = (ViewPager) findViewById(R.id.viewpager);
            mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                    mViewPagerItems));

            int defaultViewPagerItemSelectedPosition = defaultViewPagerPageSelectedPosition();
            if (defaultViewPagerItemSelectedPosition >= 0 &&
                    defaultViewPagerItemSelectedPosition < mViewPagerItems.size()) {
                mViewPager.setCurrentItem(defaultViewPagerItemSelectedPosition);
            } else defaultViewPagerItemSelectedPosition = 0;

            showIndicator(mViewPager);

            replaceTitle(mViewPagerItems
                    .get(defaultViewPagerItemSelectedPosition).getTitle());
        }

        defineDrawerLayout();
        defineListTop();
        defineListBottom();

        initFragment(savedInstanceState);
    }

    private void showIndicator(ViewPager pager) {
        if (!showViewPagerIndicator()) {
            pager.setOnPageChangeListener(mOnPageChangeListener);
        } else {
            mViewPagerIndicator = (CirclePageIndicator)
                    findViewById(R.id.viewpagerindicator);
            mViewPagerIndicator.setViewPager(pager);
            mViewPagerIndicator.setVisibility(View.VISIBLE);
            mViewPagerIndicator.setOnPageChangeListener(mOnPageChangeListener);
        }
    }

    private void replaceTitle(String title) {
        if (replaceActionBarTitleByViewPagerPageTitle()) getSupportActionBar().setTitle(title);
    }

    public abstract boolean showViewPagerIndicator();

    public abstract boolean replaceActionBarTitleByViewPagerPageTitle();

    @Override
    protected void handleListItemTopClick(View view, ListItem item, int itemPos) {
        // If this item is of type NavigationDrawerViewPagerListItem, i.e.
        // we have only added it to navigate to ViewPager using NavigationDrawer
        // then do the navigation below. If not of this type,
        // then call the super class to handle it appropriately
        if(item instanceof NavigationDrawerViewPagerListItem) {
            // Change the View in ViewPager to current Position
            try {
                String itemTitle = item.getTitle();

                // Get the ViewPager Item corresponding to the NavigationDrawer
                // Item based on the Title of Navigation Item
                int viewPosition = getViewPagerItemPositionForNavigationDrawerItemTitle(itemTitle);

                if (mViewPager != null &&
                        viewPosition != -1) {
                    mViewPager.setCurrentItem(viewPosition, true);
                }
            } catch (Exception e) {
                Log.w("NDVPTabsActivity", "Exception when switching view. Message = " + e.getMessage());
            }
            // Close the Navigation Drawer
            finally {
                closeNavigationDrawer();
            }
        }
        else{
            super.handleListItemTopClick(view, item, itemPos);
        }
    }
}
