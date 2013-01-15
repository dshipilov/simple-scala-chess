import sbt._
 
trait AssemblyProject extends BasicScalaProject {
def assemblyExclude(base: PathFinder) = base / "META-INF" ** "*"
def assemblyOutputPath = outputPath / assemblyJarName
def assemblyJarName = artifactID + "-assembly-" + version + ".jar"
def assemblyTemporaryPath = outputPath / "assembly-libs"
def assemblyClasspath = runClasspath
def assemblyExtraJars = mainDependencies.scalaJars
def assemblyPaths(tempDir: Path, classpath: PathFinder, extraJars: PathFinder, exclude: PathFinder => PathFinder) = {
val (libs, directories) = classpath.get.toList.partition(ClasspathUtilities.isArchive)
for(jar <- extraJars.get ++ libs) FileUtilities.unzip(jar, tempDir, log).left.foreach(error)  
val base = (Path.lazyPathFinder(tempDir :: directories) ##)    (descendents(base, "*") --- exclude(base)).get }   
lazy val assembly = assemblyTask(assemblyTemporaryPath, assemblyClasspath, assemblyExtraJars, assemblyExclude) dependsOn(compile)  
def assemblyTask(tempDir: Path, classpath: PathFinder, extraJars: PathFinder, exclude: PathFinder => PathFinder) =
 packageTask(Path.lazyPathFinder(assemblyPaths(tempDir, classpath, extraJars, exclude)), assemblyOutputPath, packageOptions)
}
