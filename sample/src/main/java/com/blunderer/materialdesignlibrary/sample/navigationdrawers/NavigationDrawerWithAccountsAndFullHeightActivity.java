package com.blunderer.materialdesignlibrary.sample.navigationdrawers;

import android.content.Intent;
import android.view.View;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.models.Account;
import com.blunderer.materialdesignlibrary.sample.R;
import com.blunderer.materialdesignlibrary.sample.listviews.ListViewFragment;
import com.blunderer.materialdesignlibrary.sample.scrollviews.ScrollViewFragment;
import com.blunderer.materialdesignlibrary.sample.viewpagers.ViewPagerActivity;
import com.blunderer.materialdesignlibrary.sample.viewpagers.ViewPagerFragment;
import com.blunderer.materialdesignlibrary.sample.viewpagers.ViewPagerWithTabsFragment;

public class NavigationDrawerWithAccountsAndFullHeightActivity
        extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity {

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return new NavigationDrawerAccountsHandler(this)
                .addAccount("Blunderer", "blundererandroid@gmail.com",
                        R.drawable.profile1, R.drawable.profile1_background)
                .addAccount("Blunderer's cat", "cat@gmail.com",
                        R.drawable.profile2, R.drawable.profile2_background)
                .addAccount("Blunderer's dog", "dog@gmail.com",
                        R.drawable.profile3, R.color.cyan)
                .addAccount("Blunderer's monkey", "monkey@gmail.com",
                        R.drawable.profile4, R.color.gray);
    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return new NavigationDrawerAccountsMenuHandler(this)
                .addAddAccount(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                    }

                })
                .addManageAccounts(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                    }

                });
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(this)
                .addSection(R.string.fragment)
                .addItem(R.string.fragment_listview, new ListViewFragment())
                .addItem(R.string.fragment_scrollview, new ScrollViewFragment())
                .addItem(R.string.fragment_viewpager, new ViewPagerFragment())
                .addItem(R.string.fragment_viewpager_with_tabs, new ViewPagerWithTabsFragment())
                .addSection(R.string.activity)
                .addItem(R.string.start_activity,
                        new Intent(getApplicationContext(), ViewPagerActivity.class));
    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler(this)
                .addSettings(null)
                .addHelpAndFeedback(null);
    }

    @Override
    public boolean overlayActionBar() {
        return true;
    }

    @Override
    public boolean overlayStatusBar() {
        return false;
    }

    @Override
    public boolean replaceActionBarTitleByNavigationDrawerItemTitle() {
        return true;
    }

    @Override
    public int defaultNavigationDrawerItemSelectedPosition() {
        return 1;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

}
