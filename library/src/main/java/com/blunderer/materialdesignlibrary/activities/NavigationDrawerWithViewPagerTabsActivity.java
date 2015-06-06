package com.blunderer.materialdesignlibrary.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.astuetz.PagerSlidingTabStrip;
import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.NavigationDrawerAdapter;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ListItem;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerViewPagerListItem;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.blunderer.materialdesignlibrary.views.ToolbarSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Modifications by anujsaluja on 5/30/15.
 *
 * Original Source Code Credit: Denis Mondon
 * GitHub: https://github.com/DenisMondon/material-design-library
 */
public abstract class NavigationDrawerWithViewPagerTabsActivity extends NavigationDrawerActivity
        implements com.blunderer.materialdesignlibrary.interfaces.ViewPager {

    protected ViewPager mViewPager;
    protected PagerSlidingTabStrip mViewPagerTabs;
    private List<ViewPagerItem> mViewPagerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Normal Mode - No overlays
        int contentView = R.layout.mdl_activity_navigation_drawer_with_view_pager_tabs_full;

        // Check if this is Full Screen mode - i.e. We wan to overlay the status bar
        //
        // Note: Only applicable for Lollipop and only applicable if overlayActionBar
        // is also true
        if(overlayActionBar() && overlayStatusBar()) {
            contentView = R.layout.mdl_activity_navigation_drawer_with_view_pager_tabs_full_screen;
        }

        // If this is Full Height (but not Full Screen), i.e. We only want
        // to overlayActionBar and not overlayStatusBar
        else if(overlayActionBar()) {
            contentView = R.layout.mdl_activity_navigation_drawer_with_view_pager_tabs_full;
        }

        super.onCreate(savedInstanceState, contentView);

        if (savedInstanceState != null) {
            mAccountsPositions = savedInstanceState.getIntArray("cc");
        }

        // If this is a Full Height mode (i.e overlayActionbar is true) and
        // user has also requested to make this Activity full screen, i.e.
        // overlayStatusBar is also true (NOTE: Only applicable for Lollipop and higher)
        if(overlayActionBar() && overlayStatusBar()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    Window window = getWindow();
                    window.setStatusBarColor(Color.parseColor("#00000000"));
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ViewPagerHandler handler = getViewPagerHandler();
        if (handler != null && handler.getViewPagerItems() != null) {
            mViewPagerItems = handler.getViewPagerItems();
        }

        if (mViewPagerItems != null && mViewPagerItems.size() > 0) {
            mViewPager = (ViewPager) findViewById(R.id.viewpager);
            mViewPager
                    .setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mViewPagerItems));

            int defaultViewPagerPageSelectedPosition = defaultViewPagerPageSelectedPosition();
            if (defaultViewPagerPageSelectedPosition >= 0 &&
                    defaultViewPagerPageSelectedPosition < mViewPagerItems.size()) {
                mViewPager.setCurrentItem(defaultViewPagerPageSelectedPosition);
            }

            showTabs(mViewPager);

            defineDrawerLayout();
            defineListTop();
            defineListBottom();

            initFragment(savedInstanceState);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ToolbarSearch.SEARCH_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (mCurrentItem != null && mCurrentItem.getFragment() != null) {
            mCurrentItem.getFragment().onActivityResult(requestCode, resultCode, data);
        } else if (mViewPagerItems != null && mViewPagerItems.size() > 0 && mViewPager != null) {
            int tabPosition = mViewPager.getCurrentItem();
            if (tabPosition >= 0 && tabPosition < mViewPagerItems.size()) {
                mViewPagerItems.get(tabPosition).getFragment()
                        .onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void showTabs(ViewPager pager) {
        mViewPagerTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPagerTabs.setTextColor(getResources().getColor(android.R.color.white));
        mViewPagerTabs.setShouldExpand(expandTabs());
        mViewPagerTabs.setViewPager(pager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mViewPagerTabs.setTabBackground(android.R.attr.selectableItemBackground);
        }
    }

    protected abstract boolean expandTabs();

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
