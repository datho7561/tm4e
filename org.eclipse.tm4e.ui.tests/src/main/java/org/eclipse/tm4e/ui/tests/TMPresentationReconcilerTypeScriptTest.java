/**
 * Copyright (c) 2015-2017 Angelo ZERR.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.tm4e.ui.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.eclipse.tm4e.core.grammar.IGrammar;
import org.eclipse.tm4e.core.registry.IGrammarSource;
import org.eclipse.tm4e.core.registry.Registry;
import org.eclipse.tm4e.ui.tests.support.TMEditor;
import org.eclipse.tm4e.ui.tests.support.TestUtils;
import org.eclipse.tm4e.ui.tests.support.command.ICommand;
import org.eclipse.tm4e.ui.themes.ITokenProvider;
import org.eclipse.tm4e.ui.themes.css.CSSTokenProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TMPresentationReconcilerTypeScriptTest {

	private IGrammar grammar;
	private ITokenProvider theme;
	private TMEditor editor;

	@BeforeEach
	void setup() throws Exception {
		TestUtils.assertNoTM4EThreadsRunning();

		grammar = new Registry().addGrammar(IGrammarSource.fromResource(getClass(), "/grammars/TypeScript.tmLanguage.json"));
		try (final var is = getClass().getResourceAsStream("/themes/Solarized-light.css")) {
			theme = new CSSTokenProvider(is);
		}
	}

	@AfterEach
	void tearDown() throws InterruptedException {
		editor.dispose();

		TestUtils.assertNoTM4EThreadsRunning();
	}

	@Test
	void colorizeTypescript() throws Exception {
		editor = new TMEditor(grammar, theme, "let a = '';\nlet b = 10;\nlet c = true;");

		final List<ICommand> commands = editor.execute();
		assertEquals(1, commands.size());

		final ICommand command = commands.get(0);
		assertEquals("["
				+ "StyleRange {0, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {3, 1, fontStyle=normal}, "
				+ "StyleRange {4, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {5, 1, fontStyle=normal}, "
				+ "StyleRange {6, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {7, 1, fontStyle=normal}, "
				+ "StyleRange {8, 2, fontStyle=normal, foreground=Color {42, 161, 152, 255}}, "
				+ "StyleRange {10, 2, fontStyle=normal}, "
				+ "StyleRange {12, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {15, 1, fontStyle=normal}, "
				+ "StyleRange {16, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {17, 1, fontStyle=normal}, "
				+ "StyleRange {18, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {19, 1, fontStyle=normal}, "
				+ "StyleRange {20, 2, fontStyle=normal, foreground=Color {211, 54, 130, 255}}, "
				+ "StyleRange {22, 2, fontStyle=normal}, "
				+ "StyleRange {24, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {27, 1, fontStyle=normal}, "
				+ "StyleRange {28, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {29, 1, fontStyle=normal}, "
				+ "StyleRange {30, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {31, 1, fontStyle=normal}, "
				+ "StyleRange {32, 4, fontStyle=normal, foreground=Color {181, 137, 0, 255}}, "
				+ "StyleRange {36, 1, fontStyle=normal}"
				+ "]",
				command.getStyleRanges());
	}

	@Test
	void colorizeTypescriptWithInvalidate1() throws Exception {
		editor = new TMEditor(grammar, theme, "let a = '';\nlet b = 10;\nlet c = true;");
		editor.invalidateTextPresentation(0, 3);

		final List<ICommand> commands = editor.execute();
		assertEquals(2, commands.size());

		// document.set("let a = '';\nlet b = 10;\nlet c = true;");
		final ICommand command0 = commands.get(0);
		assertEquals("["
				+ "StyleRange {0, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {3, 1, fontStyle=normal}, "
				+ "StyleRange {4, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {5, 1, fontStyle=normal}, "
				+ "StyleRange {6, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {7, 1, fontStyle=normal}, "
				+ "StyleRange {8, 2, fontStyle=normal, foreground=Color {42, 161, 152, 255}}, "
				+ "StyleRange {10, 2, fontStyle=normal}, "
				+ "StyleRange {12, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {15, 1, fontStyle=normal}, "
				+ "StyleRange {16, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {17, 1, fontStyle=normal}, "
				+ "StyleRange {18, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {19, 1, fontStyle=normal}, "
				+ "StyleRange {20, 2, fontStyle=normal, foreground=Color {211, 54, 130, 255}}, "
				+ "StyleRange {22, 2, fontStyle=normal}, "
				+ "StyleRange {24, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {27, 1, fontStyle=normal}, "
				+ "StyleRange {28, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {29, 1, fontStyle=normal}, "
				+ "StyleRange {30, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {31, 1, fontStyle=normal}, "
				+ "StyleRange {32, 4, fontStyle=normal, foreground=Color {181, 137, 0, 255}}, "
				+ "StyleRange {36, 1, fontStyle=normal}"
				+ "]",
				command0.getStyleRanges());

		// viewer.invalidateTextPresentation(0, 3);
		final ICommand command1 = commands.get(1);
		assertEquals("["
				+ "StyleRange {0, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}"
				+ "]",
				command1.getStyleRanges());
	}

	@Test
	void colorizeTypescriptWithInvalidate2() throws Exception {
		editor = new TMEditor(grammar, theme, "let a = '';\nlet b = 10;\nlet c = true;");
		editor.invalidateTextPresentation(0, 2);

		final List<ICommand> commands = editor.execute();
		assertEquals(2, commands.size());

		// document.set("let a = '';\nlet b = 10;\nlet c = true;");
		final ICommand command0 = commands.get(0);
		assertEquals("["
				+ "StyleRange {0, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {3, 1, fontStyle=normal}, "
				+ "StyleRange {4, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {5, 1, fontStyle=normal}, "
				+ "StyleRange {6, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {7, 1, fontStyle=normal}, "
				+ "StyleRange {8, 2, fontStyle=normal, foreground=Color {42, 161, 152, 255}}, "
				+ "StyleRange {10, 2, fontStyle=normal}, "
				+ "StyleRange {12, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {15, 1, fontStyle=normal}, "
				+ "StyleRange {16, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {17, 1, fontStyle=normal}, "
				+ "StyleRange {18, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {19, 1, fontStyle=normal}, "
				+ "StyleRange {20, 2, fontStyle=normal, foreground=Color {211, 54, 130, 255}}, "
				+ "StyleRange {22, 2, fontStyle=normal}, "
				+ "StyleRange {24, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {27, 1, fontStyle=normal}, "
				+ "StyleRange {28, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {29, 1, fontStyle=normal}, "
				+ "StyleRange {30, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {31, 1, fontStyle=normal}, "
				+ "StyleRange {32, 4, fontStyle=normal, foreground=Color {181, 137, 0, 255}}, "
				+ "StyleRange {36, 1, fontStyle=normal}"
				+ "]",
				command0.getStyleRanges());

		// viewer.invalidateTextPresentation(0, 2);
		final ICommand command1 = commands.get(1);
		assertEquals("["
				+ "StyleRange {0, 2, fontStyle=bold, foreground=Color {7, 54, 66, 255}}"
				+ "]",
				command1.getStyleRanges());
	}

	@Test
	void colorizeTypescriptWithInvalidate3() throws Exception {
		editor = new TMEditor(grammar, theme, "let a = '';\nlet b = 10;\nlet c = true;");
		editor.invalidateTextPresentation(1, 2);

		final List<ICommand> commands = editor.execute();
		assertEquals(2, commands.size());

		// document.set("let a = '';\nlet b = 10;\nlet c = true;");
		final ICommand Command0 = commands.get(0);
		assertEquals("["
				+ "StyleRange {0, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {3, 1, fontStyle=normal}, "
				+ "StyleRange {4, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {5, 1, fontStyle=normal}, "
				+ "StyleRange {6, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {7, 1, fontStyle=normal}, "
				+ "StyleRange {8, 2, fontStyle=normal, foreground=Color {42, 161, 152, 255}}, "
				+ "StyleRange {10, 2, fontStyle=normal}, "
				+ "StyleRange {12, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {15, 1, fontStyle=normal}, "
				+ "StyleRange {16, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {17, 1, fontStyle=normal}, "
				+ "StyleRange {18, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {19, 1, fontStyle=normal}, "
				+ "StyleRange {20, 2, fontStyle=normal, foreground=Color {211, 54, 130, 255}}, "
				+ "StyleRange {22, 2, fontStyle=normal}, "
				+ "StyleRange {24, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {27, 1, fontStyle=normal}, "
				+ "StyleRange {28, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {29, 1, fontStyle=normal}, "
				+ "StyleRange {30, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {31, 1, fontStyle=normal}, "
				+ "StyleRange {32, 4, fontStyle=normal, foreground=Color {181, 137, 0, 255}}, "
				+ "StyleRange {36, 1, fontStyle=normal}"
				+ "]",
				Command0.getStyleRanges());

		// viewer.invalidateTextPresentation(1, 2);
		final ICommand command1 = commands.get(1);
		assertEquals("["
				+ "StyleRange {1, 2, fontStyle=bold, foreground=Color {7, 54, 66, 255}}"
				+ "]",
				command1.getStyleRanges());
	}

	@Test
	void colorizeTypescriptWithInvalidate4() throws Exception {
		editor = new TMEditor(grammar, theme, "let a = '';\nlet b = 10;\nlet c = true;");
		editor.invalidateTextPresentation(1, 1);

		final List<ICommand> commands = editor.execute();
		assertEquals(2, commands.size());

		// document.set("let a = '';\nlet b = 10;\nlet c = true;");
		final ICommand command0 = commands.get(0);
		assertEquals("["
				+ "StyleRange {0, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {3, 1, fontStyle=normal}, "
				+ "StyleRange {4, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {5, 1, fontStyle=normal}, "
				+ "StyleRange {6, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {7, 1, fontStyle=normal}, "
				+ "StyleRange {8, 2, fontStyle=normal, foreground=Color {42, 161, 152, 255}}, "
				+ "StyleRange {10, 2, fontStyle=normal}, "
				+ "StyleRange {12, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {15, 1, fontStyle=normal}, "
				+ "StyleRange {16, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {17, 1, fontStyle=normal}, "
				+ "StyleRange {18, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {19, 1, fontStyle=normal}, "
				+ "StyleRange {20, 2, fontStyle=normal, foreground=Color {211, 54, 130, 255}}, "
				+ "StyleRange {22, 2, fontStyle=normal}, "
				+ "StyleRange {24, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {27, 1, fontStyle=normal}, "
				+ "StyleRange {28, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {29, 1, fontStyle=normal}, "
				+ "StyleRange {30, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {31, 1, fontStyle=normal}, "
				+ "StyleRange {32, 4, fontStyle=normal, foreground=Color {181, 137, 0, 255}}, "
				+ "StyleRange {36, 1, fontStyle=normal}"
				+ "]",
				command0.getStyleRanges());

		// viewer.invalidateTextPresentation(1, 1);
		final ICommand command1 = commands.get(1);
		assertEquals("["
				+ "StyleRange {1, 1, fontStyle=bold, foreground=Color {7, 54, 66, 255}}"
				+ "]",
				command1.getStyleRanges());
	}

	@Disabled
	@Test
	void colorizeTypescriptWithInvalidate8() throws Exception {
		editor = new TMEditor(grammar, theme, "let a = '';\nlet b = 10;\nlet c = true;");
		editor.invalidateTextPresentation(1, 8);

		final List<ICommand> commands = editor.execute();
		assertEquals(2, commands.size());

		// document.set("let a = '';\nlet b = 10;\nlet c = true;");
		final ICommand command0 = commands.get(0);
		assertEquals("["
				+ "StyleRange {0, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {3, 1, fontStyle=normal}, "
				+ "StyleRange {4, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {5, 1, fontStyle=normal}, "
				+ "StyleRange {6, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {7, 1, fontStyle=normal}, "
				+ "StyleRange {8, 2, fontStyle=normal, foreground=Color {42, 161, 152, 255}}, "
				+ "StyleRange {10, 2, fontStyle=normal}, "
				+ "StyleRange {12, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {15, 1, fontStyle=normal}, "
				+ "StyleRange {16, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {17, 1, fontStyle=normal}, "
				+ "StyleRange {18, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {19, 1, fontStyle=normal}, "
				+ "StyleRange {20, 2, fontStyle=normal, foreground=Color {211, 54, 130, 255}}, "
				+ "StyleRange {22, 2, fontStyle=normal}, "
				+ "StyleRange {24, 3, fontStyle=bold, foreground=Color {7, 54, 66, 255}}, "
				+ "StyleRange {27, 1, fontStyle=normal}, "
				+ "StyleRange {28, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
				+ "StyleRange {29, 1, fontStyle=normal}, "
				+ "StyleRange {30, 1, fontStyle=normal, foreground=Color {133, 153, 0, 255}}, "
				+ "StyleRange {31, 1, fontStyle=normal}, "
				+ "StyleRange {32, 4, fontStyle=normal, foreground=Color {181, 137, 0, 255}}, "
				+ "StyleRange {36, 1, fontStyle=normal}"
				+ "]",
				command0.getStyleRanges());

		//viewer.invalidateTextPresentation(1, 8);
		final ICommand command1 = commands.get(1);
		assertEquals("["
				+ "StyleRange {1, 2, fontStyle=bold, foreground=Color {7, 54, 66, 255}}"
				+ "]",
				command1.getStyleRanges());
	}

	@Test
	void colorizeTypescriptWithInvalidateAndSeveralLines() throws Exception {
		editor = new TMEditor(grammar, theme, "a\r\n\r\nb");
		editor.invalidateTextPresentation(0, 6);

		final List<ICommand> commands = editor.execute();
		assertEquals(2, commands.size());

		for (final ICommand command : commands) {
			assertEquals("["
					+ "StyleRange {0, 3, fontStyle=normal, foreground=Color {38, 139, 210, 255}}, "
					+ "StyleRange {3, 2, fontStyle=normal}, "
					+ "StyleRange {5, 1, fontStyle=normal, foreground=Color {38, 139, 210, 255}}"
					+ "]",
					command.getStyleRanges());
		}
	}
}
