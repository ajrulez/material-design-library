package com.blunderer.materialdesignlibrary.sample.composite;

import android.content.Intent;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.Account;
import com.blunderer.materialdesignlibrary.sample.MainFragment;
import com.blunderer.materialdesignlibrary.sample.R;
import com.blunderer.materialdesignlibrary.sample.viewpagers.ViewPagerActivity;

/**
 * Modifications by anujsaluja on 5/30/15.
 *
 * Original Source Code Credit: Denis Mondon
 * GitHub: https://github.com/DenisMondon/material-design-library
 */
public class NavigationDrawerWithViewPagerActivity
        extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerWithViewPagerActivity {
    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return null;
    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return null;
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(this)
                .addSection(R.string.navigation)
                .addItem(R.string.portfolio, new MainFragment())
                .addItem(R.string.watchlist, new MainFragment())
                .addItem(R.string.findstocks, new MainFragment())
                .addItem(R.string.topmovers, new MainFragment())
                .addItem(R.string.myaccount, new MainFragment())
                .addSection(R.string.activity)
                .addItem(R.string.start_activity, R.mipmap.ic_github,
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
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(this)
                .addPage(R.string.portfolio,
                        MainFragment.newInstance(getResources().getString(R.string.portfolio)))
                .addPage(R.string.watchlist,
                        MainFragment.newInstance(getResources().getString(R.string.watchlist)))
                .addPage(R.string.findstocks,
                        MainFragment.newInstance(getResources().getString(R.string.findstocks)))
                .addPage(R.string.topmovers,
                        MainFragment.newInstance(getResources().getString(R.string.topmovers)))
                .addPage(R.string.myaccount,
                        MainFragment.newInstance(getResources().getString(R.string.myaccount)));
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

    @Override
    public boolean showViewPagerIndicator() {
        return false;
    }

    @Override
    public boolean replaceActionBarTitleByViewPagerPageTitle() {
        return true;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

}