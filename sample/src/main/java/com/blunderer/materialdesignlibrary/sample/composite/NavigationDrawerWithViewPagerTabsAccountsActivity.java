package com.blunderer.materialdesignlibrary.sample.composite;

import android.content.Intent;
import android.view.View;

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
 * Created by anujsaluja on 5/31/15.
 */
public class NavigationDrawerWithViewPagerTabsAccountsActivity
        extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerWithViewPagerTabsActivity {

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return new NavigationDrawerAccountsHandler(this)
                .enableSmallAccountsLayout()
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
        // Note: The order of adding Navigation items to ViewPagerHandler
        // should be the same as adding those to NavigationDrawer because we
        // use index of item that is clicked in NavigationDrawer to navigate
        // to corresponding View in ViewPager.
        return new NavigationDrawerTopHandler(this)
                .addSection(R.string.navigation)
                .addItem(getResources().getString(R.string.portfolio))
                .addItem(getResources().getString(R.string.watchlist))
                .addItem(getResources().getString(R.string.findstocks))
                .addItem(getResources().getString(R.string.topmovers))
                .addItem(getResources().getString(R.string.myaccount))

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
        // Note: The order of adding Navigation items to ViewPagerHandler
        // should be the same as adding those to NavigationDrawer because we
        // use index of item that is clicked in NavigationDrawer to navigate
        // to corresponding View in ViewPager.
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
    public boolean expandTabs() {
        return false;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }
}
