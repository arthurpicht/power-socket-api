package de.arthurpicht.powerSocketApi.dummy;

import de.arthurpicht.utils.core.strings.Strings;
import de.arthurpicht.utils.io.file.TextFileUtils;
import de.arthurpicht.utils.io.nio2.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BooleanListFilePersistence {

    static BooleanList readFromTextFile(Path path) throws IOException {
        if (!FileUtils.isExistingRegularFile(path))
            throw new IllegalArgumentException("File [" + path.toAbsolutePath() + "] not found.");
        List<String> lines = TextFileUtils.readLinesAsStrings(path);
        return new BooleanList(lines);
    }

    static void saveToTextFile(Path path, BooleanList booleanList) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
        String content = Strings.listing(booleanList.asListOfStrings(), "\n");
        Files.write(path, content.getBytes());
    }

}
