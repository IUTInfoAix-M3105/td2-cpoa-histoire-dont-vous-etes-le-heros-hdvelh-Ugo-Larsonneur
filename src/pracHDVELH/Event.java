/**
 * File: NodeMultipleEvents.java
 * Creation: 7 nov. 2020, Jean-Philippe.Prost@univ-amu.fr
 * Template étudiants
 */
package pracHDVELH;

import java.util.Scanner;

import myUtils.ErrorNaiveHandler;

/**
 * @author prost
 *
 */
public class Event extends NodeMultiple {
	public static final String ERROR_MSG_UNEXPECTED_END = "Sorry, for some unexpected reason the story ends here...";
	public static final String PROMPT_ANSWER = "Answer: ";
	public static final String WARNING_MSG_INTEGER_EXPECTED = "Please input a integer within range!";

	private static GUIManager gui;
	private int id;
	static private int lastId = -1;

	private String playerAnswer;
	private int chosenPath;

	private Scanner reader;

	/**
	 * @return the playerAnswer
	 */
	public String getPlayerAnswer() {
		return playerAnswer;
	}

	/**
	 * @param playerAnswer the playerAnswer to set
	 */
	public void setPlayerAnswer(String playerAnswer) {
		this.playerAnswer = playerAnswer;
	}

	/**
	 * @return the reader
	 */
	public Scanner getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Scanner reader) {
	}

	/**
	 * @return the chosenPath
	 */
	public int getChosenPath() {
		return chosenPath;
	}

	/**
	 * @param chosenPath the chosenPath to set
	 */
	public void setChosenPath(int chosenPath) {
		this.chosenPath = chosenPath;
	}

	/* Methods */
	/**
	 * @see pracHDVELH.NodeMultiple#getData()
	 */
	public String getData() {
		return super.getData().toString();
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setData(Object)
	 * @param data
	 */
	public void setData(String data) {
		super.setData(data);
	}

	/**
	 * @see pracHDVELH.NodeMultiple#getDaughter(int)
	 */
	@Override
	public Event getDaughter(int i) {

		return new Event(gui, super.getDaughter(i).getData().toString());
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setDaughter(NodeMultiple, int)
	 * @param daughter
	 * @param i
	 */
	public void setDaughter(Event daughter, int i) {

		super.setDaughter(daughter,i);
	}

	/**
	 * @return the gui
	 */
	public GUIManager getGui() {

		return gui;
	}

	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUIManager gui) {

		this.gui = gui;
	}

	/**
	 * @return the id
	 */
	public int getId() {

		return id;
	}

	/* Methods */
	/* TO BE COMPLETED */

	public boolean isFinal(){
		return !hasDaughters();
	}

	private boolean isInRange(int index){
		return (index >= 0 && index < NodeMultiple.NODE_MAX_ARITY);
	}

	private int interpretAnswer() {

		String answer = getPlayerAnswer();

		if(!answer.matches("[1-9]"))
			ErrorNaiveHandler.abort("Not an int between 1 and 9");;

		int answerInt = Integer.parseInt(answer);

		if(!isInRange(answerInt))
			ErrorNaiveHandler.abort("Not in answer range");

		return answerInt;
	}

	@Override
	public String toString() {

		return "Event #"+id+" ("+ getClass().getName() +"): "+super.getData().toString()+".";
	}


	public Event(){
		this(new GUIManager(), "");
	}

	public Event(GUIManager gui, String data){
		super(data);
		this.gui = gui;
		this.id = ++lastId;
	}

	public Event run (){

		gui.output(getData());
		gui.output(PROMPT_ANSWER);

		playerAnswer = reader.next();
		chosenPath = interpretAnswer();

		return getDaughter(chosenPath);
	}
}

// eof