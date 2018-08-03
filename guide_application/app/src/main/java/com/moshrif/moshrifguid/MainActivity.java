package com.moshrif.moshrifguid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.moshrif.moshrifguid.auth.FirstActivity;
import com.moshrif.moshrifguid.fragments.GroupFragment;
import com.moshrif.moshrifguid.fragments.HomeFragment;
import com.moshrif.moshrifguid.fragments.MapFragment;
import com.moshrif.moshrifguid.fragments.TasksFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.moshrif.moshrifguid.FirebaseStrings.guidesRefrence;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_IMAGE_URL_CHILD;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CircularImageView image_profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            initView();
            setTabs();
            getProfiePicture();
        } else {
            gotoFirstLayout();
        }
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
        image_profile = findViewById(R.id.image_profile);
    }

    private void setTabs() {
        viewPager.setOffscreenPageLimit(4);
        viewPager.onSaveInstanceState();
        viewPager.setSaveEnabled(true);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void gotoFirstLayout() {
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "ONE");
        adapter.addFrag(new GroupFragment(), "TWO");
        adapter.addFrag(new TasksFragment(), "THREE");
        adapter.addFrag(new MapFragment(), "FOUR");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        final int[] tabIconsUnselected = {
                R.drawable.ic_home_unselected,
                R.drawable.ic_group_unselected,
                R.drawable.ic_tasks_unselected,
                R.drawable.ic_more_unselected
        };

        final int[] tabIconsSelected = {
                R.drawable.ic_home_selected,
                R.drawable.ic_group_selected,
                R.drawable.ic_tasks_selected,
                R.drawable.ic_more_selected
        };

        final TextView tabOne = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[0], 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        final TextView tabTwo = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Groups");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[1], 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        final TextView tabFour = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
        tabFour.setText("Tasks");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[2], 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabFour);

        final TextView tabFive = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
        tabFive.setText("More");
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[3], 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFive);

        tabOne.setTextColor(getResources().getColor(R.color.colorAccent));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getTabAt(0).isSelected()) {

                    tabOne.setTextColor(getResources().getColor(R.color.colorAccent));
                    tabTwo.setTextColor(getResources().getColor(R.color.black));
                    tabFour.setTextColor(getResources().getColor(R.color.black));
                    tabFive.setTextColor(getResources().getColor(R.color.black));

                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[0], 0, 0);
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[1], 0, 0);
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[2], 0, 0);
                    tabFive.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[3], 0, 0);
                } else if (tabLayout.getTabAt(1).isSelected()) {

                    tabOne.setTextColor(getResources().getColor(R.color.black));
                    tabTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    tabFour.setTextColor(getResources().getColor(R.color.black));
                    tabFive.setTextColor(getResources().getColor(R.color.black));

                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[0], 0, 0);
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[1], 0, 0);
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[2], 0, 0);
                    tabFive.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[3], 0, 0);
                } else if (tabLayout.getTabAt(2).isSelected()) {

                    tabOne.setTextColor(getResources().getColor(R.color.black));
                    tabTwo.setTextColor(getResources().getColor(R.color.black));
                    tabFour.setTextColor(getResources().getColor(R.color.colorAccent));
                    tabFive.setTextColor(getResources().getColor(R.color.black));

                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[0], 0, 0);
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[1], 0, 0);
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[2], 0, 0);
                    tabFive.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[3], 0, 0);
                } else if (tabLayout.getTabAt(3).isSelected()) {

                    tabOne.setTextColor(getResources().getColor(R.color.black));
                    tabTwo.setTextColor(getResources().getColor(R.color.black));
                    tabFour.setTextColor(getResources().getColor(R.color.black));
                    tabFive.setTextColor(getResources().getColor(R.color.colorAccent));

                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[0], 0, 0);
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[1], 0, 0);
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsUnselected[2], 0, 0);
                    tabFive.setCompoundDrawablesWithIntrinsicBounds(0, tabIconsSelected[3], 0, 0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return null to display only the icon
            //return mFragmentTitleList.get(position);
            return null;
        }
    }

    private void getProfiePicture() {
        guidesRefrence.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Picasso.with(MainActivity.this).load((String) dataSnapshot.child(GUIDE_IMAGE_URL_CHILD).getValue()).into(image_profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
