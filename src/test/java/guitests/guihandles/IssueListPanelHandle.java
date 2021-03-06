package guitests.guihandles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.saveit.model.Issue;

/**
 * Provides a handle for {@code IssueListPanel} containing the list of {@code IssueCard}.
 */
public class IssueListPanelHandle extends NodeHandle<ListView<Issue>> {
    public static final String ISSUE_LIST_VIEW_ID = "#issueListView";

    private static final String CARD_PANE_ID = "#cardPane";

    private Optional<Issue> lastRememberedSelectedIssueCard;

    public IssueListPanelHandle(ListView<Issue> issueListPanelNode) {
        super(issueListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code IssueCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public IssueCardHandle getHandleToSelectedCard() {
        List<Issue> selectedIssueList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedIssueList.size() != 1) {
            throw new AssertionError("Issue list size expected 1.");
        }

        return getAllCardNodes().stream()
                .map(IssueCardHandle::new)
                .filter(handle -> handle.equals(selectedIssueList.get(0)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the index of the selected card.
     */
    public int getSelectedCardIndex() {
        return getRootNode().getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<Issue> selectedCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display {@code issue}.
     */
    public void navigateToCard(Issue issue) {
        if (!getRootNode().getItems().contains(issue)) {
            throw new IllegalArgumentException("Issue does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(issue);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Navigates the listview to {@code index}.
     */
    public void navigateToCard(int index) {
        if (index < 0 || index >= getRootNode().getItems().size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(index);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Selects the {@code IssueCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Returns the issue card handle of an issue associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public IssueCardHandle getIssueCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(IssueCardHandle::new)
                .filter(handle -> handle.equals(getIssue(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Issue getIssue(int index) {
        return getRootNode().getItems().get(index);
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Remembers the selected {@code IssueCard} in the list.
     */
    public void rememberSelectedIssueCard() {
        List<Issue> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedIssueCard = Optional.empty();
        } else {
            lastRememberedSelectedIssueCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code IssueCard} is different from the value remembered by the most recent
     * {@code rememberSelectedIssueCard()} call.
     */
    public boolean isSelectedIssueCardChanged() {
        List<Issue> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedIssueCard.isPresent();
        } else {
            return !lastRememberedSelectedIssueCard.isPresent()
                    || !lastRememberedSelectedIssueCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
