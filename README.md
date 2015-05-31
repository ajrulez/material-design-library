Material Design Library
====================


### Description
This library helps developers creating their Android Application with Material Design.  
It offers a lot of Material Design classes easy to use like a CardView or a NavigationDrawerActivity that creates an Activity with a Material Design NavigationDrawer.

### What's new
* **SearchBar** in the ActionBar (can be used with any Activity)
* SearchBar **Auto Completion**
* NavigationDrawer **Small Accounts Layout** (to show only the current account)

### Demo
[![Material Design Library on Google Play Store](http://developer.android.com/images/brand/en_generic_rgb_wo_60.png)](https://play.google.com/store/apps/details?id=com.blunderer.materialdesignlibrary.sample)

![NavigationDrawer with Accounts](images/screen06.png) ![NavigationDrawer with Small Accounts Layout](images/screen11.png)
![CardView with Image on the Top](images/screen07.png) ![SearchBar with Auto Completion](images/screen13.png)
![ListView with Pull To Refresh](images/screen02.png) ![ViewPager with Tabs](images/screen08.png)

### Wiki
Read wiki here: [Material Design Library Wiki](https://github.com/DenisMondon/material-design-library/wiki)  

### Usage

**1.** Add the dependency in your **build.gradle**.

```groovy
dependencies {
    compile 'com.blunderer:materialdesignlibrary:2.0.0'
}
```

**2.** In your **values/styles.xml** file, change the parent style and add your own **colorPrimary/colorPrimaryDark** colors:

```xml
<style name="AppTheme" parent="@style/MaterialDesignLibraryTheme">
    <item name="colorPrimary">#3f51b5</item>
    <item name="colorPrimaryDark">#303f9f</item>
</style>
```

Or if you want the Light Theme:  

```xml
<style name="AppTheme" parent="@style/MaterialDesignLibraryTheme.Light">
    <item name="colorPrimary">#3f51b5</item>
    <item name="colorPrimaryDark">#303f9f</item>
</style>
```

**3.** Then you can:  
**-** **Extend your activity** by one of mine (*each activity will be in Material Design*):  

  * **Activity**  
    Your Activity will be a basic Activity with Material Design.

  * **ListViewActivity**  
  Your Activity will contain a ListView (with or not the Pull To Refresh).

  * **ScrollViewActivity**  
  Your Activity will contain a ScrollView (with or not the Pull To Refresh).

  * **NavigationDrawerActivity**  
  Your Activity will contain a NavigationDrawer (with or not Accounts).

  * **ViewPagerActivity**  
  Your Activity will contain a ViewPager (with or not the indicator).

  * **ViewPagerWithTabsActivity**  
  Your Activity will contain a ViewPager with tabs (expanded or not).

  * **NavigationDrawerWithViewPagerActivity**  
  Your Activity will contain both a NavigationDrawer and a ViewPager ((without Accounts only at this time).

  * **NavigationDrawerWithViewPagerTabsActivity**  
  Your Activity will contain both a NavigationDrawer and a ViewPager with Tabs ((without Accounts only at this time).

##### Example:  
```java
import com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity;

public class MyActivity extends NavigationDrawerActivity {

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return new NavigationDrawerAccountsHandler()
                .addAccount("Profile 1", "profile1@gmail.com",
                        R.drawable.profile1, R.drawable.profile1_background)
                .addAccount("Profile 2", "profile2@gmail.com",
                        R.drawable.profile2, R.drawable.profile2_background);
    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return new NavigationDrawerAccountsMenuHandler(this)
                .addAddAccount(new Intent(getApplicationContext(), AddAccountActivity.class))
                .addManageAccounts(new Intent(getApplicationContext(), ManageAccountsActivity.class));
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
        Toast.makeText(getApplicationContext(), "Account changed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler()
                .addItem(R.string.title_item1, new MainFragment())
                .addItem(R.string.title_item2, new MainFragment())
                .addSection(R.string.title_section2)
                .addItem(R.string.title_item3, new MainFragment())
                .addItem(R.string.title_item4, new Intent(this, MyActivity.class));
    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler()
                .addSettings(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(intent);
                    }

                })
                .addHelpAndFeedback(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), HelpAndFeedbackActivity.class);
                        startActivity(intent);
                    }

                });
    }

    @Override
    public boolean overlayActionBar() {
        return true;
    }

    @Override
    public boolean replaceActionBarTitleByNavigationDrawerItemTitle() {
        return true;
    }

    @Override
    public int defaultNavigationDrawerItemSelectedPosition() {
        return 0;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

}
```

**-** Or use my **CardView**:

  * **Normal CardView**  
  Your CardView will be a basic CardView.

  * **Left Image CardView**  
  Your CardView will contain an image on the left.

  * **Top Image CardView**  
  Your CardView will contain an image on the top.

##### Example:  
```xml
<com.blunderer.materialdesignlibrary.views.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:mdl_title="CardView"
        app:mdl_description="A Left Image CardView"
        app:mdl_normalButton="Normal"
        app:mdl_highlightButton="Highlight"
        app:mdl_imagePosition="left"
        app:mdl_image="@drawable/image" />
```

### Developed by

 * Denis Mondon - <blundererandroid@gmail.com>
 * NavigationDrawerWithViewPagerActivity and NavigationDrawerWithViewPagerTabsActivity - Anuj Saluja - <alifesoftware@gmail.com>
