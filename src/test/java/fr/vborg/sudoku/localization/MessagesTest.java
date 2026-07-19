package fr.vborg.sudoku.localization;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.MissingResourceException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MessagesTest
{
    private static final Locale DEFAULT_LOCALE = Locale.FRENCH;

    @AfterEach
    void restoreLocale()
    {
        Messages.setLocale(DEFAULT_LOCALE);
    }

    @Nested
    class LocaleManagement
    {
        @Test
        void shouldReturnCurrentLocale()
        {
            Messages.setLocale(Locale.FRENCH);

            assertEquals(
                    Locale.FRENCH,
                    Messages.getLocale());
        }

        @Test
        void shouldChangeLocale()
        {
            Messages.setLocale(Locale.FRENCH);

            assertEquals(
                    Locale.FRENCH,
                    Messages.getLocale());
        }

        @Test
        void shouldRejectNullLocale()
        {
            assertThrows(
                    NullPointerException.class,
                    () -> Messages.setLocale(null));
        }
    }


    @Nested
    class MessageRetrieval
    {
        @Test
        void shouldReturnFrenchMessage()
        {
            Messages.setLocale(Locale.FRENCH);

            assertEquals(
                    "Ligne",
                    Messages.get("console.row"));
        }

        @Test
        void shouldReturnAllFrenchMessages()
        {
            Messages.setLocale(Locale.FRENCH);

            assertAll(
                    () -> assertEquals(
                            "Ligne",
                            Messages.get("console.row")),

                    () -> assertEquals(
                            "Colonne",
                            Messages.get("console.column")),

                    () -> assertEquals(
                            "Valeur",
                            Messages.get("console.value")),

                    () -> assertEquals(
                            "Saisissez un coup",
                            Messages.get("console.move")),

                    () -> assertEquals(
                            "Coup invalide",
                            Messages.get("console.invalid")),

                    () -> assertEquals(
                            "Tapez 'quit' pour quitter",
                            Messages.get("console.quit"))
            );
        }

        @Test
        void shouldRejectNullKey()
        {
            assertThrows(
                    NullPointerException.class,
                    () -> Messages.get(null));
        }

        @Test
        void shouldThrowExceptionForUnknownKey()
        {
            assertThrows(
                    MissingResourceException.class,
                    () -> Messages.get("unknown.key"));
        }
    }


    @Nested
    class MessageFormatting
    {
        @Test
        void shouldFormatMessageWithArguments()
        {
            /*
             * Nécessite :
             *
             * test.message=Bonjour {0}
             *
             * dans messages_fr.properties
             */
            assertEquals(
                    "Bonjour Jean",
                    Messages.get(
                            "test.message",
                            "Jean"));
        }

        @Test
        void shouldRejectNullArguments()
        {
            assertThrows(
                    NullPointerException.class,
                    () -> Messages.get(
                            "test.message",
                            (Object[]) null));
        }
    }


    @Nested
    class KeyExistence
    {
        @Test
        void shouldReturnTrueForExistingKey()
        {
            assertTrue(
                    Messages.contains("console.row"));
        }

        @Test
        void shouldReturnFalseForUnknownKey()
        {
            assertFalse(
                    Messages.contains("unknown.key"));
        }

        @Test
        void shouldRejectNullKey()
        {
            assertThrows(
                    NullPointerException.class,
                    () -> Messages.contains(null));
        }
    }


    @Nested
    class BundleReload
    {
        @Test
        void shouldReloadBundle()
        {
            Messages.reload();

            assertEquals(
                    "Ligne",
                    Messages.get("console.row"));
        }
    }
}