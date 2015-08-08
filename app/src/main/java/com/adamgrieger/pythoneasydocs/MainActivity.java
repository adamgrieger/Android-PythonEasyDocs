package com.adamgrieger.pythoneasydocs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.KeyboardUtil;


public class MainActivity extends AppCompatActivity {

    private static final int ADD_DOCS_ID = 1;
    private static final int MANAGE_DOCS_ID = 2;

    private AccountHeader mAccountHeader = null;
    private Drawer mDrawer = null;

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
                .withProfileImagesClickable(false)
                .addProfiles(
                        new ProfileSettingDrawerItem().withName("Add Documentation").withIdentifier(ADD_DOCS_ID).withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_icon)).withIdentifier(ADD_DOCS_ID),
                        new ProfileSettingDrawerItem().withName("Manage Documentation").withIdentifier(MANAGE_DOCS_ID).withIcon(GoogleMaterial.Icon.gmd_settings)
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
                .withHeaderDivider(false)
                .withSelectedItem(1)
                .withAnimateDrawerItems(true)
                .withCloseOnClick(false)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.drawer_header_doc_parts),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_whats_new).withIcon(GoogleMaterial.Icon.gmd_new_releases),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_tutorial).withIcon(GoogleMaterial.Icon.gmd_school),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_lib_ref).withIcon(GoogleMaterial.Icon.gmd_library_books),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_lang_ref).withIcon(GoogleMaterial.Icon.gmd_language),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_setup_usage).withIcon(GoogleMaterial.Icon.gmd_settings_applications),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_howtos).withIcon(GoogleMaterial.Icon.gmd_help),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_install_modules).withIcon(GoogleMaterial.Icon.gmd_file_download),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_distrib_modules).withIcon(GoogleMaterial.Icon.gmd_file_upload),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_extend_embed).withIcon(GoogleMaterial.Icon.gmd_extension),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_python_c_api).withIcon(GoogleMaterial.Icon.gmd_memory),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_faqs).withIcon(GoogleMaterial.Icon.gmd_question_answer),
                        new SectionDrawerItem().withName(R.string.drawer_header_indices_tables),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_global_module_ind).withIcon(GoogleMaterial.Icon.gmd_public),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_gen_ind).withIcon(GoogleMaterial.Icon.gmd_view_module),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_glossary).withIcon(GoogleMaterial.Icon.gmd_view_headline),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_table_contents).withIcon(GoogleMaterial.Icon.gmd_apps),
                        new SectionDrawerItem().withName(R.string.drawer_header_meta_info),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_report_bugs).withIcon(GoogleMaterial.Icon.gmd_bug_report),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_about_doc).withIcon(GoogleMaterial.Icon.gmd_info),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_history_license).withIcon(GoogleMaterial.Icon.gmd_history),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_copyright).withIcon(GoogleMaterial.Icon.gmd_content_copy)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            getSupportActionBar().setTitle(((Nameable) drawerItem).getNameRes());
                            Fragment f = DocViewerFragment.newInstance(((Nameable) drawerItem).getName());
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();
                        }

                        return false;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        KeyboardUtil.hideKeyboard(MainActivity.this);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
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
