/**
 * 
 */
package com.filescanner;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
/**
 * @author Roopam
 *
 */
public class MyFileVisitor extends SimpleFileVisitor<Path> {

	@Override
	public FileVisitResult postVisitDirectory(Path arg0, IOException arg1)
			throws IOException {
		System.out.println("Just visited"+ arg0);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path arg0, BasicFileAttributes arg1)
			throws IOException {
		System.out.println("About to be visited"+ arg0);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path arg0, BasicFileAttributes arg1)
			throws IOException {
		if(arg1.isRegularFile()){
			System.out.println("Regular file");
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path arg0, IOException arg1)
			throws IOException {
		System.out.println("Error Occurred");
		return FileVisitResult.CONTINUE;
	}

}
