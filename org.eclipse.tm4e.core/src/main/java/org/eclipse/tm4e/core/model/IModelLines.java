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
package org.eclipse.tm4e.core.model;

/**
 * Model lines API which must be initalized with a document and changed of document.
 */
public interface IModelLines {

	/**
	 * Add a new line at specified index line.
	 *
	 * @param lineIndex (0-based)
	 */
	void addLine(int lineIndex);

	/**
	 * Remove the line at specified index line.
	 *
	 * @param lineIndex (0-based)
	 */
	void removeLine(int lineIndex);

	/**
	 * Mark a line as updated.
	 *
	 * @param lineIndex (0-based)
	 */
	void updateLine(int lineIndex);

	boolean hasLine(int lineIndex);

	int getNumberOfLines();

	/**
	 * @param lineIndex (0-based)
	 */
	String getLineText(int lineIndex) throws Exception;

	int getLineLength(int lineIndex) throws Exception;

	void dispose();
}
