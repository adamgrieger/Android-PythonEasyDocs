package com.adamgrieger.pythoneasydocs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.octicons_typeface_library.Octicons;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final int PARTS_DOCUMENTATION_ID = 1;
    private static final int INDICES_TABLES_ID = 2;
    private static final int META_INFORMATION_ID = 3;

    private static final int ADD_DOCS_ID = 100;
    private static final int MANAGE_DOCS_ID = 200;

    private AccountHeader mAccountHeader = null;
    private Drawer mDrawer = null;

    private boolean isFirstGroupExpanded = false;
    private boolean isSecondGroupExpanded = false;
    private boolean isThirdGroupExpanded = false;

    private JSONParser jParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jParse = new JSONParser(getApplicationContext());

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Python 3.4.3").withNameShown(true).withEmail("February 25th, 2015")
                                .withIcon(new IconicsDrawable(this, FontAwesome.Icon.faw_file_pdf_o).actionBar().paddingDp(5).backgroundColorRes(R.color.md_light_blue_500).colorRes(R.color.material_drawer_dark_primary_text)),
                        new ProfileSettingDrawerItem().withName("Add Documentation").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_icon)),
                        new ProfileSettingDrawerItem().withName("Manage Documentation").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        if (profile instanceof IDrawerItem && profile.getIdentifier() == ADD_DOCS_ID) {
                            // TODO: Add an indented list of available and not yet downloaded docs
                        } else if (profile instanceof IDrawerItem && profile.getIdentifier() == MANAGE_DOCS_ID) {
                            // TODO: Add a delete button next to downloaded docs
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(mAccountHeader)
                .withCloseOnClick(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_header_doc_parts).withIcon(GoogleMaterial.Icon.gmd_library_books).withIdentifier(PARTS_DOCUMENTATION_ID),
                        new PrimaryDrawerItem().withName(R.string.drawer_header_indices_tables).withIcon(GoogleMaterial.Icon.gmd_apps).withIdentifier(INDICES_TABLES_ID),
                        new PrimaryDrawerItem().withName(R.string.drawer_header_meta_info).withIcon(GoogleMaterial.Icon.gmd_info).withIdentifier(META_INFORMATION_ID),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Contact").withIcon(FontAwesome.Icon.faw_contao),
                        new SecondaryDrawerItem().withName("Open Source").withIcon(Octicons.Icon.oct_octoface),
                        new SecondaryDrawerItem().withName("Help").withIcon(GoogleMaterial.Icon.gmd_help),
                        new SwitchDrawerItem().withName("Dark Mode").withIcon(GoogleMaterial.Icon.gmd_brightness_low).withTextColorRes(R.color.material_drawer_secondary_text)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof PrimaryDrawerItem) {
                            if (drawerItem.getIdentifier() == PARTS_DOCUMENTATION_ID && !isFirstGroupExpanded) {
                                isFirstGroupExpanded = true;
                                ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
                                drawerItems.addAll(mDrawer.getDrawerItems());
                                int drawerItemIndex = drawerItems.indexOf(drawerItem);

                                drawerItems.add(drawerItemIndex + 1, new SecondaryDrawerItem().withName(R.string.drawer_item_whats_new));
                                drawerItems.add(drawerItemIndex + 2, new SecondaryDrawerItem().withName(R.string.drawer_item_tutorial));
                                drawerItems.add(drawerItemIndex + 3, new SecondaryDrawerItem().withName(R.string.drawer_item_lib_ref));
                                drawerItems.add(drawerItemIndex + 4, new SecondaryDrawerItem().withName(R.string.drawer_item_lang_ref));
                                drawerItems.add(drawerItemIndex + 5, new SecondaryDrawerItem().withName(R.string.drawer_item_setup_usage));
                                drawerItems.add(drawerItemIndex + 6, new SecondaryDrawerItem().withName(R.string.drawer_item_howtos));
                                drawerItems.add(drawerItemIndex + 7, new SecondaryDrawerItem().withName(R.string.drawer_item_install_modules));
                                drawerItems.add(drawerItemIndex + 8, new SecondaryDrawerItem().withName(R.string.drawer_item_distrib_modules));
                                drawerItems.add(drawerItemIndex + 9, new SecondaryDrawerItem().withName(R.string.drawer_item_extend_embed));
                                drawerItems.add(drawerItemIndex + 10, new SecondaryDrawerItem().withName(R.string.drawer_item_python_c_api));
                                drawerItems.add(drawerItemIndex + 11, new SecondaryDrawerItem().withName(R.string.drawer_item_faqs));
                                drawerItems.add(drawerItemIndex + 12, new DividerDrawerItem());

                                mDrawer.setItems(drawerItems);
                            } else if (drawerItem.getIdentifier() == PARTS_DOCUMENTATION_ID) {
                                isFirstGroupExpanded = false;
                                ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
                                drawerItems.addAll(mDrawer.getDrawerItems());
                                int drawerItemIndex = drawerItems.indexOf(drawerItem);

                                for (int i = 0; i < 12; i++) {
                                    drawerItems.remove(drawerItemIndex + 1);
                                }

                                mDrawer.setItems(drawerItems);
                            } else if (drawerItem.getIdentifier() == INDICES_TABLES_ID && !isSecondGroupExpanded) {
                                isSecondGroupExpanded = true;
                                ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
                                drawerItems.addAll(mDrawer.getDrawerItems());
                                int drawerItemIndex = drawerItems.indexOf(drawerItem);

                                drawerItems.add(drawerItemIndex + 1, new SecondaryDrawerItem().withName(R.string.drawer_item_global_module_ind));
                                drawerItems.add(drawerItemIndex + 2, new SecondaryDrawerItem().withName(R.string.drawer_item_gen_ind));
                                drawerItems.add(drawerItemIndex + 3, new SecondaryDrawerItem().withName(R.string.drawer_item_glossary));
                                drawerItems.add(drawerItemIndex + 4, new SecondaryDrawerItem().withName(R.string.drawer_item_table_contents));
                                drawerItems.add(drawerItemIndex + 5, new DividerDrawerItem());

                                mDrawer.setItems(drawerItems);
                            } else if (drawerItem.getIdentifier() == INDICES_TABLES_ID) {
                                isSecondGroupExpanded = false;
                                ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
                                drawerItems.addAll(mDrawer.getDrawerItems());
                                int drawerItemIndex = drawerItems.indexOf(drawerItem);

                                for (int i = 0; i < 5; i++) {
                                    drawerItems.remove(drawerItemIndex + 1);
                                }

                                mDrawer.setItems(drawerItems);
                            } else if (drawerItem.getIdentifier() == META_INFORMATION_ID && !isThirdGroupExpanded) {
                                isThirdGroupExpanded = true;
                                ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
                                drawerItems.addAll(mDrawer.getDrawerItems());
                                int drawerItemIndex = drawerItems.indexOf(drawerItem);

                                drawerItems.add(drawerItemIndex + 1, new SecondaryDrawerItem().withName(R.string.drawer_item_report_bugs));
                                drawerItems.add(drawerItemIndex + 2, new SecondaryDrawerItem().withName(R.string.drawer_item_about_doc));
                                drawerItems.add(drawerItemIndex + 3, new SecondaryDrawerItem().withName(R.string.drawer_item_history_license));
                                drawerItems.add(drawerItemIndex + 4, new SecondaryDrawerItem().withName(R.string.drawer_item_copyright));

                                mDrawer.setItems(drawerItems);
                            } else if (drawerItem.getIdentifier() == META_INFORMATION_ID) {
                                isThirdGroupExpanded = false;
                                ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
                                drawerItems.addAll(mDrawer.getDrawerItems());
                                int drawerItemIndex = drawerItems.indexOf(drawerItem);

                                for (int i = 0; i < 4; i++) {
                                    drawerItems.remove(drawerItemIndex + 1);
                                }

                                mDrawer.setItems(drawerItems);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        mDrawer.keyboardSupportEnabled(this, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = mDrawer.saveInstanceState(outState);
        outState = mAccountHeader.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
