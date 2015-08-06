package com.adamgrieger.pythoneasydocs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final int ADD_DOCS_ID = 1;
    private static final int MANAGE_DOCS_ID = 2;

    private static final String TAG = "MainActivity";
    
    private Context context = getApplicationContext();

    private AccountHeader mAccountHeader = null;
    private Drawer mDrawer = null;

    private String[] supportedVersions = {
            "3.4.3",
            "3.3.6",
            "2.7.10"
    };

    private ArrayList<PythonDocument> availableDocs;
    private ArrayList<PythonDocument> downloadedDocs;

    private File availableJSON = new File(context.getFilesDir(), "available_docs.json");
    private File downloadedJSON = new File(context.getFilesDir(), "downloaded_docs.json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (availableJSON.exists() && downloadedJSON.exists()) {
            loadJSONFiles();
        } else {
            createNewJSONFiles();
        }

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .withProfileImagesClickable(false)
                .addProfiles(
                        new ProfileSettingDrawerItem().withName("Add Documentation").withIdentifier(ADD_DOCS_ID).withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).sizeDp(12).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(ADD_DOCS_ID),
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

    public void createNewJSONFiles() {
        deleteJSONFiles();

        try {
            if (availableJSON.createNewFile()) {
                Log.i(TAG, "available_docs.json has been created");
            }

            if (downloadedJSON.createNewFile()) {
                Log.i(TAG, "downloaded_docs.json has been created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String version : supportedVersions) {
            availableDocs.add(new PythonDocument(version));
        }

        Gson gson = new Gson();
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput("available_docs.json", Context.MODE_PRIVATE);
            outputStream.write(gson.toJson(availableDocs).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteJSONFiles() {
        if (availableJSON.delete()) {
            Log.i(TAG, "available_docs.json has been deleted");
        } else {
            Log.i(TAG, "available_docs.json cannot be deleted, because it does not exist");
        }

        if (downloadedJSON.delete()) {
            Log.i(TAG, "downloaded_docs.json has been deleted");
        } else {
            Log.i(TAG, "downloaded_docs.json cannot be deleted, because it does not exist");
        }
    }

    public void loadJSONFiles() {
        Gson gson = new Gson();

        try {
            availableDocs = gson.fromJson(new BufferedReader(new FileReader(availableJSON)), new TypeToken<ArrayList<PythonDocument>>(){}.getType());
            downloadedDocs = gson.fromJson(new BufferedReader(new FileReader(downloadedJSON)), new TypeToken<ArrayList<PythonDocument>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateJSONFiles() {
        deleteJSONFiles();

        try {
            FileOutputStream outputStream = context.openFileOutput("available_docs.json", Context.MODE_PRIVATE);
            outputStream.write(new Gson().toJson(availableDocs).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream outputStream = context.openFileOutput("downloaded_docs.json", Context.MODE_PRIVATE);
            outputStream.write(new Gson().toJson(downloadedDocs).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
