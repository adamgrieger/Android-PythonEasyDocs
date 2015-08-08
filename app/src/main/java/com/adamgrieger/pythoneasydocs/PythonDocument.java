package com.adamgrieger.pythoneasydocs;


/**
 * A class for storing information about a specific version and format of Python documentation.
 */
public class PythonDocument {

    private String pythonVersion, releaseDate;
    private String downloadURL, downloadSize;

    public PythonDocument(String pythonVersion, String format) {
        this.pythonVersion = pythonVersion;

        switch (pythonVersion) {
            case "3.4.3":
                releaseDate = "February 25, 2015";

                if (format.equalsIgnoreCase("HTML")) {
                    downloadURL = "https://docs.python.org/ftp/python/doc/3.4.3/python-3.4.3-docs-html.zip";
                    downloadSize = "6 MB";
                } else if (format.equalsIgnoreCase("PDF")) {
                    downloadURL = "https://docs.python.org/ftp/python/doc/3.4.3/python-3.4.3-docs-pdf-letter.zip";
                    downloadSize = "8 MB";
                } else if (format.equalsIgnoreCase("TXT")) {
                    downloadURL = "https://docs.python.org/ftp/python/doc/3.4.3/python-3.4.3-docs-text.zip";
                    downloadSize = "2 MB";
                }

                break;
            case "3.3.6":
                releaseDate = "October 11, 2014";

                if (format.equalsIgnoreCase("HTML")) {
                    downloadURL = "http://docs.python.org/ftp/python/doc/3.3.6/python-3.3.6-docs-html.zip";
                    downloadSize = "6 MB";
                } else if (format.equalsIgnoreCase("PDF")) {
                    downloadURL = "http://docs.python.org/ftp/python/doc/3.3.6/python-3.3.6-docs-pdf-letter.zip";
                    downloadSize = "8 MB";
                } else if (format.equalsIgnoreCase("TXT")) {
                    downloadURL = "http://docs.python.org/ftp/python/doc/3.3.6/python-3.3.6-docs-text.zip";
                    downloadSize = "2 MB";
                }

                break;
            case "2.7.10":
                releaseDate = "May 23, 2015";

                if (format.equalsIgnoreCase("HTML")) {
                    downloadURL = "https://docs.python.org/2.7/archives/python-2.7.10-docs-html.zip";
                    downloadSize = "6 MB";
                } else if (format.equalsIgnoreCase("PDF")) {
                    downloadURL = "https://docs.python.org/2.7/archives/python-2.7.10-docs-pdf-letter.zip";
                    downloadSize = "8 MB";
                } else if (format.equalsIgnoreCase("TXT")) {
                    downloadURL = "https://docs.python.org/2.7/archives/python-2.7.10-docs-text.zip";
                    downloadSize = "2 MB";
                }

                break;
        }
    }

    public void downloadDocument() {

    }

    // -----------
    // | Getters |
    // -----------

    public String getPythonVersion() {
        return pythonVersion;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public String getDownloadSize() {
        return downloadSize;
    }

    // -----------
    // | Setters |
    // -----------

    public void setPythonVersion(String pythonVersion) {
        this.pythonVersion = pythonVersion;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public void setDownloadSize(String downloadSize) {
        this.downloadSize = downloadSize;
    }
}
