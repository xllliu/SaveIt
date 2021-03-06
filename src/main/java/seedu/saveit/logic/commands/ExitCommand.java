package seedu.saveit.logic.commands;

import seedu.saveit.commons.core.EventsCenter;
import seedu.saveit.commons.events.ui.ExitAppRequestEvent;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String COMMAND_ALIAS = "x";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting SaveIt as requested ...";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        EventsCenter.getInstance().post(new ExitAppRequestEvent());
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

}
