package de.arthurpicht.powerSocketApi.dummy;

import de.arthurpicht.utils.io.nio2.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DummyFilePersistence {

    private final List<String> outletNames;
    private final Path dummyPersistenceFile;

    public DummyFilePersistence(List<String> outletNames) {
        this.outletNames = outletNames;
        this.dummyPersistenceFile = FileUtils.getHomeDir().resolve(".dummyPowerModule.persistence");
    }

    public boolean get(String outletName) throws IOException {
        if (!this.outletNames.contains(outletName))
            throw new IllegalArgumentException("Outlet " + outletName + " does not exist");
        BooleanList booleanList = obtainBooleanList();

        int index = this.outletNames.indexOf(outletName);
        return booleanList.get(index);
    }

    public void set(String outletName, boolean value) throws IOException {
        if (!this.outletNames.contains(outletName))
            throw new IllegalArgumentException("Outlet " + outletName + " does not exist");
        BooleanList booleanList = obtainBooleanList();

        int index = this.outletNames.indexOf(outletName);
        booleanList.set(index, value);

        BooleanListFilePersistence.saveToTextFile(this.dummyPersistenceFile, booleanList);
    }

    private BooleanList obtainBooleanList() throws IOException {
        if (FileUtils.isExistingRegularFile(this.dummyPersistenceFile)) {
            return BooleanListFilePersistence.readFromTextFile(this.dummyPersistenceFile);
        } else {
            return new BooleanList();
        }
    }

}
