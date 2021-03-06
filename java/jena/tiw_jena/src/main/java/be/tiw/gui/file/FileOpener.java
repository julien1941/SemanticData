package be.tiw.gui.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.jena.rdf.model.Model;

import be.tiw.gui.file.OntologyFileFilter;

public class FileOpener
{
	public static boolean openFile(Model model)
	{
		return FileOpener.openFile(pickFile(), model);
	}
	public static boolean openFile(String selectedFile, Model model)
	{
		try
		{
			File file = new File(selectedFile);
			return FileOpener.openFile(file, model);
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "No ontology selected", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	public static boolean openFile(File selectedFile, Model model)
	{
		try
		{
			if(selectedFile==null)
			{
				return false;
			}
			
			InputStream ontologyFile = new FileInputStream(selectedFile);
			model.read(ontologyFile, "RDF/XML");
			ontologyFile.close();
			
			return true;
		}
		catch(Exception e)
		{
			if(selectedFile == null)
			{
				JOptionPane.showMessageDialog(null, "No ontology selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error with the file " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
	}
	private static File pickFile()
	{
		JFileChooser chooser = new JFileChooser("D:\\Documents\\UNIVERSITE\\Bloc5\\Quadri 2\\SemanticData\\Project\\SemanticData");
		chooser.setFileFilter(new OntologyFileFilter());
		
		File selectedFile;
		
		chooser.showOpenDialog(null);
		selectedFile=chooser.getSelectedFile();
		
		return selectedFile;
	}
}
