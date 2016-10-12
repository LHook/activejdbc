package org.javalite.activejdbc.validation.inclusion;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Validates that the attribute is included in a given list.
 *
 * E.g. Setting the list to ["Dune", "Ender's Game"] and then submitting a Book with the title "Dune" or "Ender's Game"
 * will cause this validation to pass because we want to include the Book titles "Dune" and "Ender's Game".
 * Any other Book title will cause validation to fail.
 */
public class AttributeInclusionValidator extends ValidatorAdapter {

    private final String attribute;
    private List list;

    private AttributeInclusionValidator(final String attribute) {
        this.attribute = attribute;
        setMessage("{0} is not included in the list.");
    }

    public static AttributeInclusionValidator on(final String attribute) {
        return new AttributeInclusionValidator(attribute);
    }

    @Override
    public void validate(final Model m) {
        Object o = m.get(attribute);
        if (!list.contains(o)) {
            m.addValidator(this, attribute);
        }
    }

    public AttributeInclusionValidator with(final List list) {
        this.list = list;
        return this;
    }

    public AttributeInclusionValidator with(final Object... items) {
        this.list = Arrays.asList(items);
        return this;
    }

    @Override
    public String formatMessage(final Locale locale, final Object... params) {
        return super.formatMessage(locale, attribute);
    }
}