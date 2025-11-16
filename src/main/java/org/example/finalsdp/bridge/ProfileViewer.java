package org.example.finalsdp.bridge;

public abstract class ProfileViewer {
    protected DisplayFormat format;

    public ProfileViewer(DisplayFormat displayFormat) {
        this.format = displayFormat;
    }

    public void changeDisplayFormat(DisplayFormat newFormat) {
        this.format = newFormat;
    }

    public abstract void renderProfile();
}

