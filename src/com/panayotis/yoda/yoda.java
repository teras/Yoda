/*
 * @(#)CalculatorMIDlet.java	1.4 03/10/01
 *
 * Copyright (c) 2000-2003 Sun Microsystems, Inc. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL
 * Use is subject to license terms
 */
package com.panayotis.yoda;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import java.util.Random;

/**
 * The calculator demo is a simple floating point calculator
 * which powered by floating point support available in cldc1.1.
 *
 * @version 1.4
 */
public final class yoda extends MIDlet implements CommandListener {

	private static final String ansString = "I seek Answers";
	private static final String wisString = "I seek Jedi Wisdom";

	private static final String[] answers = {
		"Clear your mind of questions.",
		"Decide you must.",
		"Always in motion is the future.",
		"Use the Force.",
		"Difficult to see.",
		"You will know when you are calm, at peace, passive.",
		"Matters are worse.",
		"No.",
		"Yes.",
		"I sense much fear in you.",
		"Afraid are you?"};
	

	private static final String[] wisdom = {
		"A Jedi must have the deepest commitment, the most serious mind.",
		"Adventure. Heh! Excitement. Heh! A Jedi craves not these things.",
		"A Jedi's strength flows from the Force.",
		"Beware the Dark Side.",
		"Anger. Fear. Aggression. The Dark Side of the Force are they.",
		"Once you start down the Dark Path, forever will it dominate your destiny, consume you it will...",
		"The Dark Side is quicker, easier, more seductive.",
		"A Jedi uses the Force for knowledge and defense, never for attack.",
		"You must unlearn what you have learned.",
		"Try not. Do, or do not. There is no try.",
		"Size matters not.",
		"My ally is the Force. And a powerful ally it is.",
		"Luminous beings are we, not this crude matter!",
		"You must feel the force around you.",
		"Control! You must learn control!",
		"Ready are you? What know you of ready?",
		"Why wish you become Jedi?",
		"Help you I can, yes.",
		"Patience!",
		"Wars not make one great.",
		"Concentrate!",
		"Fear is the path to the Dark Side.",
		"Fear leads to anger, anger leads to hate, hate leads to suffering.",
		"Always two there are, no more, no less... a Master and an Apprentice.",
		"May the Force be with you.",
		"Hard to see, the Dark Side is.",
		"Concentrate... feel the Force flow.",
		"Through the Force, things you will see. Other places. The future... the past. Old friends long gone.",
		"Only a fully trained Jedi Knight with the Force as his ally will conquer.",
		"If you end your training now, if you choose the quick and easy path, you will become an agent of evil.",
		"Mind what you have learned. Save you it can.",
		"When nine hundred years old you reach, look as good you will not.",
		"That is the way of things ... the way of the Force.",
		"Already know you that which you need."};


    /** Soft button for exiting the game. */
    private final Command exitCmd  = new Command("Exit", Command.EXIT, 1);

    /** Menu item for changing game levels. */
    private final Command ansCmd = new Command("Answers", ansString, Command.SCREEN, 2);
	private final Command wisCmd = new Command("Wisdom", wisString, Command.SCREEN, 3);
    private final Command infCmd = new Command("Info", Command.SCREEN, 4);
    private final Command abtCmd = new Command("About", Command.SCREEN, 4);

    /** Indicates if the application is initialized. */
    private boolean isInitialized = false;

    /**
     * Creates the Yoda Application
     */
    protected void startApp() {
        if (isInitialized) {
            return;
        }
		
        Form f = new Form("Yoda");
        f.addCommand(exitCmd);
        f.addCommand(ansCmd);
        f.addCommand(wisCmd);
        f.addCommand(infCmd);
        f.addCommand(abtCmd);
        f.setCommandListener(this);
        Display.getDisplay(this).setCurrent(f);
        isInitialized = true;
		
		StringItem welcomemsg = new StringItem (null, "I am wondering, why are you here?");
		f.append(welcomemsg);
		
		try {
			Image yodaimg = ( (f.getHeight()-welcomemsg.getPreferredHeight()) > 100 ) ? Image.createImage("/icons/yodabig.png") : Image.createImage("/icons/yodasmall.png");
			ImageItem yodapic = new ImageItem ("", yodaimg, ImageItem.LAYOUT_CENTER, "");
			f.append(yodapic);
		}
		catch (java.io.IOException e) {
		}
		
    }

    /**
     * Does nothing. Redefinition is required by MIDlet class.
     */
    protected void destroyApp(boolean unconditional) {}

    /**
     * Does nothing. Redefinition is required by MIDlet class.
     */
    protected void pauseApp() {}

    /**
     * Responds to commands issued on CalculatorForm.
     *
     * @param c command object source of action
     * @param d screen object containing actioned item
     */
    public void commandAction(Command c, Displayable d) {
        if (c == exitCmd) {
            destroyApp(false);
            notifyDestroyed();
            return;
        }

		String label = null;
		String text  = null;
		
		Random rnd = new Random();
		if ( c == ansCmd) {
			label=ansString;
			text = answers[Math.abs(rnd.nextInt()) % answers.length];
		}
		if ( c == wisCmd) {
			label=wisString;
			text = wisdom[Math.abs(rnd.nextInt()) % wisdom.length];
		}
		if ( c == infCmd) {
			label = "Yoda 1.0 information";
			text = "You have come to Jedi Master Yoda for guidance.\n\nIf you select on 'I seek Jedi Wisdom', Yoda will educate you in the ways of the Force.\n\nIf you ask a question and select on 'I seek Answers', Yoda will look to the future and help you along. (like magic 8ball)";
		}
		if ( c == abtCmd) {
			label = "Yoda 1.0";
			text = "The code of program is under the GPL licence and copyrighted by Panayotis Katsaloulis (panayotis@panayotis.com)\n\nBased on the Yoda program for the Palm platform by Pete Moss (bantha@bigfoot.com)\nGraphics by Robert Hagenstroem(robert.hagenstrom@ebox.tninet.se)\n\nGeorge Lucas owns all of the words and ideas in this program {\"I would get his permission if only he would return my calls...  :)\"}";
		}
			
		Alert alert = new Alert(label, text, null, AlertType.INFO);
		alert.setTimeout(Alert.FOREVER);
		Display.getDisplay(this).setCurrent(alert);
    }

} // end of class 'yoda' definition
