package com.example.administrator.readnews;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.readnews.R;

public class News_Activity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private  Bundle args;
    private ImageView imgheader;
private TextView txtheader;
    String title;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.list);
        title=getIntent().getStringExtra("url");
        setSupportActionBar(toolbar);
        args = new Bundle();


        // Find our drawer view
        mDrawer =  findViewById(R.id.drawer_layout);

        nvDrawer =  findViewById(R.id.nvView);
        txtheader=nvDrawer.getHeaderView(0).findViewById(R.id.txtheader);
        imgheader=nvDrawer.getHeaderView(0).findViewById(R.id.imgheader);
        imgheader.setImageResource(R.drawable.vne);
        setTitle("Tin mới");
        // Setup drawer view
        setupDrawerContent(nvDrawer);
        Fragment fragment=new HomeFragment();
        args.putString("title",title);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        AddStatus();

        Menu menuNav = nvDrawer.getMenu();
//        MenuItem logoutItem = menuNav.findItem(R.id.vnbandoc);

//        logoutItem.setVisible(true);

//        Menu menu = nvDrawer.getMenu();
//        menu.removeGroup(R.id.vnExpress);
//        menu.add(0, 0, 0, "Option1").setShortcut('3', 'c');
//        menu.add(0, 1, 0, "Option2").setShortcut('3', 'c');
//        menu.add(0, 2, 0, "Option3").setShortcut('4', 's');

        if(title.equals("vnExpress")){
            menuNav.setGroupVisible(R.id.vnExpress,true);
            txtheader.setText("vnExpress");
            imgheader.setImageResource(R.drawable.vne);
        }else if(title.equals("Dân trí")){
            menuNav.setGroupVisible(R.id.dantri,true);
            txtheader.setText("Dân trí");
            imgheader.setImageResource(R.drawable.dantri);
        }else if(title.equals("Thanh niên")){
            menuNav.setGroupVisible(R.id.thanhnien,true);
            txtheader.setText("Thanh niên");
            imgheader.setImageResource(R.drawable.thanhnien);
        }
        else if(title.equals("Kiến thức")){
            menuNav.setGroupVisible(R.id.vietnamnet,true);
            txtheader.setText("Kiến thức");
            imgheader.setImageResource(R.drawable.kienthuc);
        }
        else if(title.equals("Sơn Hà")){
            menuNav.setGroupVisible(R.id.sonha,true);
            txtheader.setText("Sơn Hà");
            imgheader.setImageResource(R.drawable.sonha);
        }
        else if(title.equals("vtc")){
            menuNav.setGroupVisible(R.id.vtc,true);
            txtheader.setText("VTC");
            imgheader.setImageResource(R.drawable.vtc);
        }
        else if(title.equals("tienphong")){
            menuNav.setGroupVisible(R.id.tienphong,true);
            txtheader.setText("Tiền Phong");
            imgheader.setImageResource(R.drawable.tienphong);
        }
        else if(title.equals("vietbao")){
            menuNav.setGroupVisible(R.id.vietbao,true);
            txtheader.setText("Việt Báo");
            imgheader.setImageResource(R.drawable.vietbao);
        }

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

        txtheader.setText(title);
        args.putString("title",title);
        switch(menuItem.getItemId()) {
            case R.id.mnkhoahoc:
                args.putString("url", "https://www.tienphong.vn/rss/ho-chi-minh-288.rss");
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
            case R.id.shgiaotri:
                args.putString("url", "http://soha.vn/giai-tri.rss");
                break;
            case R.id.shthethao:
                args.putString("url", "http://soha.vn/the-thao.rss");
                break;
            case R.id.shthoisu:
                args.putString("url", "http://soha.vn/thoi-su.rss");
                break;
            case R.id.shphapluat:
                args.putString("url", "http://soha.vn/phap-luat.rss");
                break;
            case R.id.shkinhdoanh:
                args.putString("url", "http://soha.vn/kinh-doanh.rss");
                break;
            case R.id.shquocte:
                args.putString("url", "http://soha.vn/quoc-te.rss");
                break;
            case R.id.shsongkhoe:
                args.putString("url", "http://soha.vn/song-khoe.rss");
                break;
            case R.id.shquansu:
                args.putString("url", "http://soha.vn/quan-su.rss");
                break;
            case R.id.shcudanmang:
                args.putString("url", "http://soha.vn/cu-dan-mang.rss");
                break;
            case R.id.shinfograghicc:
                args.putString("url", "http://soha.vn/infographic.rss");
                break;
            case R.id.shkhampha:
                args.putString("url", "http://soha.vn/kham-pha.rss");
                break;
            case R.id.dtsuckhoe:
                args.putString("url", "http://dantri.com.vn/suc-khoe.rss");
                break;
            case R.id.dtungthu:
                args.putString("url", "http://dantri.com.vn/suc-khoe/ung-thu.rss");
                break;
            case R.id.dtlamdep:
                args.putString("url", "http://dantri.com.vn/suc-khoe/lam-dep.rss");
                break;
            case R.id.dttuvan:
                args.putString("url", "http://dantri.com.vn/suc-khoe/tu-van.rss");
                break;
            case R.id.dtkienthucgioitinh:
                args.putString("url", "http://dantri.com.vn/suc-khoe/kien-thuc-gioi-tinh.rss");
                break;
            case R.id.dtxahoi:
                args.putString("url", "http://dantri.com.vn/suc-khoe/xa-hoi.rss");
                break;
            case R.id.dtkisuphongsu:
                args.putString("url", "http://dantri.com.vn/suc-khoe/kí-su-phong-su.rss");
                break;
            case R.id.dtmoitruong:
                args.putString("url", "http://dantri.com.vn/suc-khoe/moi-truong.rss");
                break;
            case R.id.dtchinhtri:
                args.putString("url", "http://dantri.com.vn/suc-khoe/chinh-tri.rss");
                break;
            case R.id.dthoso:
                args.putString("url", "http://dantri.com.vn/suc-khoe/ho-so.rss");
                break;
            case R.id.dtgiaothong:
                args.putString("url", "http://dantri.com.vn/suc-khoe/giao-thong.rss");
                break;
            case R.id.shdoisong:
                args.putString("url", "http://soha.vn/doi-song.rss");
                break;
            case R.id.dtgiaitri:
                args.putString("url", "http://dantri.com.vn/suc-khoe/giai-tri.rss");
                break;
            case R.id.dtthoitrang:
                args.putString("url", "http://dantri.com.vn/suc-khoe/thoi-trang.rss");
                break;

            case R.id.tnthoisu:
                args.putString("url", "https://thanhnien.vn/rss/viet-nam.rss");
                break;
            case R.id.tnphapluat:
                args.putString("url", "https://thanhnien.vn/rss/viet-nam/phap-luat.rss");
                break;
            case R.id.tntrongan:
                args.putString("url", "https://thanhnien.vn/rss/phap-luat/trong-an.rss");
                break;
            case R.id.tnphongsu:
                args.putString("url", "https://thanhnien.vn/rss/chinh-tri-xa-hoi/phong-su.rss");
                break;
            case R.id.tnquocphong:
                args.putString("url", "https://thanhnien.vn/rss/thoi-su/quoc-phong.rss");
                break;
            case R.id.tndocquyen:
                args.putString("url", "https://thanhnien.vn/rss/chinh-tri-xa-hoi/doc-quyen.rss");
                break;
            case R.id.tntinhhuong:
                args.putString("url", "https://thanhnien.vn/rss/T%C3%ACnh%20hu%E1%BB%91ng.rss");
                break;
            case R.id.tndansinh:
                args.putString("url", "https://thanhnien.vn/rss/doi-song/dan-sinh.rss");
                break;
            case R.id.tnvieclam:
                args.putString("url", "https://thanhnien.vn/rss/thoi-su/viec-lam.rss");
                break;
            case R.id.tncanbiet:
                args.putString("url", "https://thanhnien.vn/rss/viec-lam/can-biet.rss");
                break;
            case R.id.tnnghehot:
                args.putString("url", "https://thanhnien.vn/rss/viec-lam/nghe-hot.rss");
                break;
            case R.id.tnkinangtimviec:
                args.putString("url", "https://thanhnien.vn/rss/viec-lam/ky-nang-tim-viec.rss");
                break;
            case R.id.tntuyendung:
                args.putString("url", "https://thanhnien.vn/rss/viec-lam/tuyen-dung.rss");
                break;
            case R.id.vnphapluat:
                args.putString("url", "https://kienthuc.net.vn/rss/xa-hoi-24.rss");
                break;
            case R.id.vncongnghe:
                args.putString("url", "https://kienthuc.net.vn/rss/the-gioi-25.rss");
                break;
            case R.id.vnkinhdoanh:
                args.putString("url", "https://kienthuc.net.vn/rss/cong-dong-tre-27.rss");
                break;
            case R.id.vngiaoduc:
                args.putString("url", "https://kienthuc.net.vn/rss/video-214.rss");
                break;
            case R.id.vnthoisu:
                args.putString("url", "https://kienthuc.net.vn/rss/kinh-doanh-9.rss");
                break;
            case R.id.vngiaitri:
                args.putString("url", "https://kienthuc.net.vn/rss/quan-su-26.rss");
                break;
            case R.id.vnsuckhoe:
                args.putString("url", "https://kienthuc.net.vn/rss/kho-tri-thuc-8.rss");
                break;
            case R.id.vnthethao:
                args.putString("url", "https://kienthuc.net.vn/rss/kham-pha-13.rss");
                break;
            case R.id.vndoisong:
                args.putString("url", "https://kienthuc.net.vn/rss/cong-nghe-235.rss");
                break;

            case R.id.vnthegioi:
                args.putString("url", "https://kienthuc.net.vn/rss/lan-banh-217.rss");
                break;
            case R.id.vtxahoi:
                args.putString("url", "http://vtc.vn/xa-hoi.rss");
                break;
            case R.id.vtkinhte:
                args.putString("url", "http://vtc.vn/kinh-te.rss");
                break;
            case R.id.vttruyenhinh:
                args.putString("url", "http://vtc.vn/truyen-hinh.rss");
                break;
            case R.id.vtphapluat:
                args.putString("url", "http://vtc.vn/phap-luat.rss");
                break;
            case R.id.vtthegioi:
                args.putString("url", "http://vtc.vn/the-gioi.rss");
                break;
            case R.id.vtdiaoc:
                args.putString("url", "http://vtc.vn/dia-oc.rss");
                break;
            case R.id.vtgiaitri:
                args.putString("url", "http://vtc.vn/giai-tri.rss");
                break;
            case R.id.vtthethao:
                args.putString("url", "http://vtc.vn/the-thao.rss");
                break;
            case R.id.vtgioitre:
                args.putString("url", "http://vtc.vn/gioi-tre.rss");
                break;
            case R.id.tptintuc:
                args.putString("url", "https://www.tienphong.vn/rss/xa-hoi-tin-tuc-104.rss");
                break;
            case R.id.tptrada:
                args.putString("url", "https://www.tienphong.vn/rss/xa-hoi-tra-nong-tra-da-114.rss");
                break;
            case R.id.tpchuyenhomnay:
                args.putString("url", "https://www.tienphong.vn/rss/xa-hoi-chuyen-hom-nay-18.rss");
                break;
            case R.id.tpphongsu:
                args.putString("url", "https://www.tienphong.vn/rss/xa-hoi-phong-su-13.rss");
                break;
                case R.id.tpthitruong:
                args.putString("url", "https://www.tienphong.vn/rss/kinh-te-thi-truong-24.rss");
                break;
                case R.id.tpdoanhnghiep:
                args.putString("url", "https://www.tienphong.vn/rss/kinh-te-doanh-nghiep-22.rss");
                break;
                case R.id.tpdoanhnhan:
                args.putString("url", "https://www.tienphong.vn/rss/kinh-te-doanh-nhan-165.rss");
                break;
            case R.id.tpchungkhoan:
                args.putString("url", "https://www.tienphong.vn/rss/kinh-te-chung-khoan-105.rss");
                break;
            case R.id.tpdulich:
                args.putString("url", "https://www.tienphong.vn/rss/du-lich-220.rss");
                break;
            case R.id.tpnhipsong:
                args.putString("url", "https://www.tienphong.vn/rss/gioi-tre-nhip-song-27.rss");
                break;
            case R.id.tpvieclam:
                args.putString("url", "https://www.tienphong.vn/rss/gioi-tre-viec-lam-102.rss");
                break;
            case R.id.tptainangtre:
                args.putString("url", "https://www.tienphong.vn/rss/tai-nang-tre-295.rss");
                break; case R.id.tpsao:
                args.putString("url", "https://www.tienphong.vn/rss/giai-tri-sao-35.rss");
                break;
            case R.id.tpcongnghe:
                args.putString("url", "https://www.tienphong.vn/rss/cong-nghe-khoa-hoc-46.rss");
                break; case R.id.tpthoitrang:
                args.putString("url", "https://www.tienphong.vn/rss/thoi-trang-266.rss");
                break;
            case R.id.tphocduong:
                args.putString("url", "https://www.tienphong.vn/rss/hoc-duong-ky-tuc-xa-194.rss");
                break;
                case R.id.vbanninh:
                args.putString("url", "http://vietbao.vn/live/An-ninh-Phap-luat/rss.xml");
                break;
            case R.id.vbblog:
                args.putString("url", "http://vietbao.vn/live/Blog/rss.xml");
                break;
            case R.id.vbbongda:
                args.putString("url", "http://vietbao.vn/live/Bong-da/rss.xml");
                break;
            case R.id.vbchiemtinh:
                args.putString("url", "http://vietbao.vn/live/Chiem-tinh/rss.xml");
                break;
            case R.id.vbcongnghe:
                args.putString("url", "http://vietbao.vn/live/Cong-nghe/rss.xml");
                break;
            case R.id.vbdulich:
                args.putString("url", "http://vietbao.vn/live/Du-lich/rss.xml");
                break;
            case R.id.vbdoisong:
                args.putString("url", "http://vietbao.vn/live/Doi-song-Gia-dinh/rss.xml");
                break;

            case R.id.vbgame:
                args.putString("url", "http://vietbao.vn/live/Game/rss.xml");
                break;
            case R.id.vbgiaoduc:
                args.putString("url", "http://vietbao.vn/live/Giao-duc/rss.xml");
                break;
            case R.id.vbkhampha:
                args.putString("url", "http://vietbao.vn/live/Kham-pha-Viet-Nam/rss.xml");
                break;
            case R.id.vbkhoahoc:
                args.putString("url", "http://vietbao.vn/live/Khoa-hoc/rss.xml");
                break;
            case R.id.vbkinhte:
                args.putString("url", "http://vietbao.vn/live/Kinh-te/rss.xml");
                break;
            case R.id.vbmedia:
                args.putString("url", "http://vietbao.vn/live/Media/rss.xml");
                break;
            case R.id.vbbonphuong:
                args.putString("url", "http://vietbao.vn/live/Nguoi-Viet-bon-phuong/rss.xml");
                break;
            case R.id.vbnhadat:
                args.putString("url", "http://vietbao.vn/live/Nha-dat/rss.xml");
                break;
            case R.id.vboto:
                args.putString("url", "http://vietbao.vn/live/O-to-xe-may/rss.xml");
                break;
            case R.id.vbphongsu:
                args.putString("url", "http://vietbao.vn/live/Phong-su/rss.xml");
                break;
            case R.id.vbsuckhoe:
                args.putString("url", "http://vietbao.vn/live/Suc-khoe/rss.xml");
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
