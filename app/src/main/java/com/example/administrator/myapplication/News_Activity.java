package com.example.administrator.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class News_Activity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private  Bundle args;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.check);
        setSupportActionBar(toolbar);
        args = new Bundle();


        // Find our drawer view
        mDrawer =  findViewById(R.id.drawer_layout);
        nvDrawer =  findViewById(R.id.nvView);
        setTitle("Tin mới");
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        Fragment fragment=new HomeFragment();
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        AddStatus();

        Menu menuNav = nvDrawer.getMenu();
        MenuItem logoutItem = menuNav.findItem(R.id.mnkhoahoc);
        logoutItem.setVisible(true);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.navigation, menu);
//        MenuItem menuItem= menu.findItem(R.id.mnkhoahoc);
//        menuItem.setVisible(false);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = OtherContentFragment.class;
        switch(menuItem.getItemId()) {
            case R.id.mnkhoahoc:

                args.putString("url", "https://vnexpress.net/rss/khoa-hoc.rss");


                break;
            case R.id.mngiaitri:
                args.putString("url", "https://vnexpress.net/rss/giai-tri.rss");
                break;
            case R.id.mnxe:
                args.putString("url", "https://vnexpress.net/rss/oto-xe-may.rss");
                break;
            case R.id.mngiaoduc:
                args.putString("url", "https://vnexpress.net/rss/giao-duc.rss");
                break;
            case R.id.mnthoisu:
                args.putString("url", "https://vnexpress.net/rss/thoi-su.rss");
                break;
            case R.id.mnthethao:
                args.putString("url", "https://vnexpress.net/rss/the-thao.rss");
                break;
            case R.id.mnphapluat:
                args.putString("url", "https://vnexpress.net/rss/phap-luat.rss");
                break;
            case R.id.mnstartup:
                args.putString("url", "https://vnexpress.net/rss/startup.rss");
                break;
            case R.id.mnsuckhoe:
                args.putString("url", "https://vnexpress.net/rss/suc-khoe.rss");
                break;
            case R.id.mngiadinh:
                args.putString("url", "https://vnexpress.net/rss/gia-dinh.rss");
                break;
            default:

                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

//         Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private  void AddStatus()
    { Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
//       getSupportActionBar().hide();
    }
}
