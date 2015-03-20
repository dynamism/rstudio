/*
 * SnippetType.java
 *
 * Copyright (C) 2009-15 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.workbench.snippets.ui;

import org.rstudio.studio.client.common.filetypes.TextFileType;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class EditableSnippets extends Composite
{
   public EditableSnippets(TextFileType fileType)
   {
      this(fileType.getLabel(), fileType);
   }
   
   public EditableSnippets(String name, TextFileType fileType)
   {
      fileType_ = fileType;
      HorizontalPanel panel = new HorizontalPanel();
      Image icon = new Image(fileType.getDefaultIcon());
      icon.getElement().getStyle().setMarginRight(2, Unit.PX);
      panel.add(icon);
      Label label = new Label(name);    
      label.getElement().getStyle().setMarginLeft(5, Unit.PX);
      panel.add(label);
      initWidget(panel);
   }
   
   public String getSnippetText()
   {
      // if we haven't yet been edited then get the default snippet text
      if (pendingEdits_ != null)
      {
         return pendingEdits_;
      }
      else
      {
         return getSnippetText(fileType_.getEditorLanguage().getModeName());
      } 
   }
    
   public void recordPendingEdits(String pendingEdits)
   {
      pendingEdits_ = pendingEdits;
   }
   
   public void setScrollPosition(int scrollPosition)
   {
      scrollPosition_ = scrollPosition;
   }
   
   public int getScrollPosition()
   {
      return scrollPosition_;
   }
   
   public String getPendingEdits()
   {
      return pendingEdits_;
   }
   
   private static native String getSnippetText(String mode) /*-{
      var snippetId = "ace/snippets/" + mode;
      return $wnd.require(snippetId).snippetText;
   }-*/;
   
   private final TextFileType fileType_;
   private String pendingEdits_ = null;
   private int scrollPosition_ = 0;
}
