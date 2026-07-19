package fr.vborg.sudoku.localization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ExternalResourceBundleControl}.
 *
 * @since 1.0
 */
class ExternalResourceBundleControlTest
{
    /**
     * External configuration directory used by the implementation.
     */
    private static final Path CONFIG_DIRECTORY =
            Path.of("config");


    /**
     * Removes temporary configuration files after each test.
     *
     * @throws Exception if cleanup fails
     */
    @AfterEach
    void cleanup() throws Exception
    {
        if (Files.exists(CONFIG_DIRECTORY))
        {
        	try (Stream<Path> files = Files.list(CONFIG_DIRECTORY))
        	{
        	    files.forEach(path ->
        	    {
        	        try
        	        {
        	            Files.deleteIfExists(path);
        	        }
        	        catch (IOException exception)
        	        {
        	            throw new RuntimeException(exception);
        	        }
        	    });
        	}

            Files.deleteIfExists(CONFIG_DIRECTORY);
        }
    }


    /**
     * Tests that external configuration files override classpath resources.
     *
     * @throws Exception if file creation fails
     */
    @Test
    void should_load_external_bundle_before_classpath()
            throws Exception
    {
        Files.createDirectories(CONFIG_DIRECTORY);

        Files.writeString(
                CONFIG_DIRECTORY.resolve(
                        "messages_fr.properties"),
                "console.row=Ligne"
        );


        ResourceBundle bundle =
                ResourceBundle.getBundle(
                        "messages",
                        Locale.FRENCH,
                        new ExternalResourceBundleControl()
                );


        assertEquals(
                "Ligne",
                bundle.getString("console.row")
        );
    }


    /**
     * Tests that classpath resources are used when no external file exists.
     */
    @Test
    void should_fallback_to_classpath_bundle()
    {
        ResourceBundle bundle =
                ResourceBundle.getBundle(
                        "messages",
                        Locale.ENGLISH,
                        new ExternalResourceBundleControl()
                );


        assertEquals(
                "Row",
                bundle.getString("console.row")
        );
    }


    /**
     * Tests that locale-specific external bundles are loaded.
     *
     * @throws Exception if file creation fails
     */
    @Test
    void should_load_external_bundle_for_requested_locale()
            throws Exception
    {
        Files.createDirectories(CONFIG_DIRECTORY);

        Files.writeString(
                CONFIG_DIRECTORY.resolve(
                        "messages_en.properties"),
                "console.row=Row"
        );


        ResourceBundle bundle =
                ResourceBundle.getBundle(
                        "messages",
                        Locale.ENGLISH,
                        new ExternalResourceBundleControl()
                );


        assertEquals(
                "Row",
                bundle.getString("console.row")
        );
    }
}