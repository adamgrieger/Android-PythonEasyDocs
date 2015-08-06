package com.adamgrieger.pythoneasydocs;


/**
 * A class for storing information about available Python documentation.
 */
public class PythonDocument {

    private String pythonVersion, releaseDate;
    private String urlHTML, urlPDF, urlTXT;
    private String sizeHTML, sizePDF, sizeTXT;

    public PythonDocument(String v) {
        pythonVersion = v;

        switch (v) {
            case "3.4.3":
                releaseDate = "February 25, 2015";
                urlHTML = "https://docs.python.org/ftp/python/doc/3.4.3/python-3.4.3-docs-html.zip";
                urlPDF = "https://docs.python.org/ftp/python/doc/3.4.3/python-3.4.3-docs-pdf-letter.zip";
                urlTXT = "https://docs.python.org/ftp/python/doc/3.4.3/python-3.4.3-docs-text.zip";
                sizeHTML = "6 MB";
                sizePDF = "8 MB";
                sizeTXT = "2 MB";
                break;
            case "3.3.6":
                releaseDate = "October 11, 2014";
                urlHTML = "http://docs.python.org/ftp/python/doc/3.3.6/python-3.3.6-docs-html.zip";
                urlPDF = "http://docs.python.org/ftp/python/doc/3.3.6/python-3.3.6-docs-pdf-letter.zip";
                urlTXT = "http://docs.python.org/ftp/python/doc/3.3.6/python-3.3.6-docs-text.zip";
                sizeHTML = "6 MB";
                sizePDF = "8 MB";
                sizeTXT = "2 MB";
                break;
            case "2.7.10":
                releaseDate = "May 23, 2015";
                urlHTML = "https://docs.python.org/2.7/archives/python-2.7.10-docs-html.zip";
                urlPDF = "https://docs.python.org/2.7/archives/python-2.7.10-docs-pdf-letter.zip";
                urlTXT = "https://docs.python.org/2.7/archives/python-2.7.10-docs-text.zip";
                sizeHTML = "6 MB";
                sizePDF = "8 MB";
                sizeTXT = "2 MB";
                break;
        }
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

    public String getUrlHTML() {
        return urlHTML;
    }

    public String getUrlPDF() {
        return urlPDF;
    }

    public String getUrlTXT() {
        return urlTXT;
    }

    public String getSizeHTML() {
        return sizeHTML;
    }

    public String getSizePDF() {
        return sizePDF;
    }

    public String getSizeTXT() {
        return sizeTXT;
    }

    // -----------
    // | Setters |
    // -----------

    public void setPythonVersion(String v) {
        pythonVersion = v;
    }

    public void setReleaseDate(String date) {
        releaseDate = date;
    }

    public void setUrlHTML(String url) {
        urlHTML = url;
    }

    public void setUrlPDF(String url) {
        urlPDF = url;
    }

    public void setUrlTXT(String url) {
        urlTXT = url;
    }

    public void setSizeHTML(String size) {
        sizeHTML = size;
    }

    public void setSizePDF(String size) {
        sizePDF = size;
    }

    public void setSizeTXT(String size) {
        sizeTXT = size;
    }
}
