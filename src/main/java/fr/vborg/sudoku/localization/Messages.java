package fr.vborg.sudoku.localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Provides localized messages.
 *
 * @since 1.0
 */
public final class Messages
{
    /**
     * Base name of the resource bundle.
     */
    private static final String BUNDLE_NAME = "messages";

    /**
     * Current locale.
     */
    private static Locale locale = Locale.getDefault();

    /**
     * Current resource bundle.
     */
    private static ResourceBundle bundle =
            ResourceBundle.getBundle(
                    BUNDLE_NAME,
                    locale,
                    new ExternalResourceBundleControl());

    /**
     * Utility class.
     */
    private Messages()
    {
    }

    /**
     * Changes the current locale.
     *
     * @param newLocale the new locale
     */
    public static void setLocale(final Locale newLocale)
    {
        locale = Objects.requireNonNull(newLocale);

        bundle = ResourceBundle.getBundle(
                BUNDLE_NAME,
                locale,
                new ExternalResourceBundleControl());
    }

    /**
     * Returns the current locale.
     *
     * @return the current locale
     */
    public static Locale getLocale()
    {
        return locale;
    }

    /**
     * Returns a localized message.
     *
     * @param key the key
     * @return the localized message
     */
    public static String get(final String key)
    {
        return bundle.getString(
                Objects.requireNonNull(key));
    }

    /**
     * Returns a formatted localized message.
     *
     * @param key the key
     * @param arguments the message arguments
     * @return the formatted message
     */
    public static String get(
            final String key,
            final Object... arguments)
    {
        return MessageFormat.format(
                get(key),
                arguments);
    }
}