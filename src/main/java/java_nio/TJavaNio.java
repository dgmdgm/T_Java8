package java_nio;

import com.sun.beans.finder.FieldFinder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 */
public class TJavaNio
{
    private static final Logger LOG = Logger.getLogger(TJavaNio.class);

    public static void main(final String ... args)
    {
//        t_Filestore();
        t_FileVisitor();
    }


    public static void t_Filestore()
    {
        final FileSystem fileSystem = FileSystems.getDefault();
        LOG.trace("t_Filestore: fileSystem=" + ToStringBuilder.reflectionToString(fileSystem));

        final Iterable<FileStore> fileStores = fileSystem.getFileStores();
        LOG.trace("t_Filestore: fileStores=" + ToStringBuilder.reflectionToString(fileStores));
        for (final FileStore fileStore : fileStores)
        {
            LOG.trace("t_Filestore: fileStores=" + ToStringBuilder.reflectionToString(fileStore));
        }

    }


    public static void t_FileVisitor()
    {
        final Path root = Paths.get("C:/-");
        LOG.trace("t_FileVisitor: root=" + root);

        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*media?books*");
        final ArrayList<Path> matchingPaths = new ArrayList<>();

        final FileVisitor fileVisitor = new SimpleFileVisitor<Path>()
        {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
            {
                LOG.trace("preVisitDirectory: dir=" + dir +", attrs=" + ToStringBuilder.reflectionToString(attrs));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path  file, BasicFileAttributes attrs) throws IOException
            {
                LOG.trace("visitFile: file=" + file +", attrs=" + ToStringBuilder.reflectionToString(attrs));

                final Path finemame = file.getFileName();
                if (pathMatcher.matches(finemame))
                {
                    LOG.trace("visitFile: matching");
                    matchingPaths.add(file);
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
            {
                LOG.trace("visitFileFailed: file=" + file + ", exc=" + exc);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
            {
                LOG.trace("postVisitDirectory: dir= " + dir + ", exc=" + exc);
                return FileVisitResult.CONTINUE;
            }
        };

        try
        {
            Files.walkFileTree(root,fileVisitor);

            LOG.trace("There are " + matchingPaths.size() + " matches.");
            for (final Path path : matchingPaths)
            {
                LOG.trace("  path=: " + path + ", realpath=" + path.toRealPath());
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
