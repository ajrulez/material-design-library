package com.blunderer.materialdesignlibrary.sample.composite;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.listeners.OnAccountOptionClickListener;
import com.blunderer.materialdesignlibrary.models.Account;
import com.blunderer.materialdesignlibrary.sample.MainFragment;
import com.blunderer.materialdesignlibrary.sample.R;
import com.blunderer.materialdesignlibrary.sample.viewpagers.ViewPagerActivity;

/**
 * Created by anujsaluja on 5/31/15.
 */
public class NavigationDrawerWithViewPagerTabsAccountsActivity
        extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerWithViewPagerTabsActivity {

    private OnAccountOptionClickListener accountOptionClickListener = new OnAccountOptionClickListener() {
        @Override
        public void onAccountOptionClicked(String optionText) {
            Toast.makeText(NavigationDrawerWithViewPagerTabsAccountsActivity.this, optionText, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return new NavigationDrawerAccountsHandler(this)
                .enableSmallAccountsLayout() // Comment this if you want bigger Account area
                .addAccount("Manage Account", "ajrulez@gmail.com",
                        R.drawable.profile1, R.drawable.profile1_background)
                .addAccountOption("Export Portfolio",
                        "Exports Portfolio to HTML File",
                        accountOptionClickListener)
                .addAccountOption("Export Watchlist",
                        "Exports Watchlist to HTML File",
                        accountOptionClickListener)
                .addAccountOption("Add \\ Remove Countries",
                        "Allows user to add or remove countries",
                        accountOptionClickListener)
                .addAccountOption("Reset Account",
                        "Resets and clears user account",
                        accountOptionClickListener)
                .addAccountOption("Log Off",
                        "Logs the user off",
                        accountOptionClickListener);
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

        // Prepare the NavigationDrawer to ViewPager item map
        addNavigationViewPagerMapping(getResources().getString(R.string.portfolio), 1);
        addNavigationViewPagerMapping(getResources().getString(R.string.watchlist), 2);
        addNavigationViewPagerMapping(getResources().getString(R.string.findstocks), 3);
        addNavigationViewPagerMapping(getResources().getString(R.string.topmovers), 4);
        addNavigationViewPagerMapping(getResources().getString(R.string.myaccount), 5);
        addNavigationViewPagerMapping(getResources().getString(R.string.cramerspicks), 6);
        addNavigationViewPagerMapping(getResources().getString(R.string.insidertrades), 7);

        return new NavigationDrawerTopHandler(this)
                .addSection(R.string.navigation)
                .addItem(getResources().getString(R.string.portfolio))
                .addItem(getResources().getString(R.string.watchlist))
                .addItem(getResources().getString(R.string.findstocks))
                .addItem(getResources().getString(R.string.topmovers))
                .addItem(getResources().getString(R.string.myaccount))
                .addItem(getResources().getString(R.string.cramerspicks))
                .addItem(getResources().getString(R.string.insidertrades))

                .addSection(R.string.licenseandinfo)
                .addItem(R.string.privacypolicy, R.mipmap.ic_github,
                        new Intent(getApplicationContext(), ViewPagerActivity.class))
                .addItem(R.string.licence, R.mipmap.ic_github,
                        new Intent(getApplicationContext(), ViewPagerActivity.class))
                .addItem(R.string.disclaimer, R.mipmap.ic_github,
                        new Intent(getApplicationContext(), ViewPagerActivity.class))
                .addItem(R.string.aboutus, R.mipmap.ic_github,
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
        return false;
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
                        MainFragment.newInstance(getResources().getString(R.string.myaccount)))
                .addPage(R.string.cramerspicks,
                        MainFragment.newInstance(getResources().getString(R.string.cramerspicks)))
                .addPage(R.string.insidertrades,
                        MainFragment.newInstance(getResources().getString(R.string.insidertrades)));
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
