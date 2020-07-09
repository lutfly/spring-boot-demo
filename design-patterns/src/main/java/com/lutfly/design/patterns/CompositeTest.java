package com.lutfly.design.patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Sunny软件公司欲开发一个杀毒(AntiVirus)软件，该软件既可以对某个文件夹(Folder)杀毒，也
 * 可以对某个指定的文件(File)进行杀毒。该杀毒软件还可以根据各类文件的特点，为不同类型
 * 的文件提供不同的杀毒方式，例如图像文件(ImageFile)和文本文件(TextFile)的杀毒方式就有所
 * 差异。现需要提供该杀毒软件的整体框架设计方案。
 *
 * Author	Date	Changes
 * fengy  2020/7/10 Created
 */
public class CompositeTest {

    public static void main(String[] args) {
        AbstractFile file1 = new File();
        AbstractFile file2 = new File();
        AbstractFile file3 = new File();
        AbstractFile file4 = new File();
        AbstractFile file5 = new File();
        AbstractFile file6 = new File();
        AbstractFile file7 = new File();
        Folder folder1 = new Folder();
        Folder folder2 = new Folder();
        Folder folder3 = new Folder();

        folder1.addFile(file1);
        folder1.addFile(file2);
        folder2.addFile(file3);
        folder2.addFile(file4);
        folder2.addFile(folder1);
        folder3.addFile(file5);
        folder3.addFile(file6);
        folder3.addFile(file7);
        folder3.addFile(folder2);

        folder3.killVirus();
    }

}
interface AbstractFile{
    void killVirus();
}

class Folder implements AbstractFile{

    public List<AbstractFile> files = new ArrayList<>();

    public void addFile(AbstractFile file){
        files.add(file);
    }

    @Override
    public void killVirus() {
        System.out.println("kill folder virus "+this.getClass().getName());

        for (AbstractFile file : files) {
            file.killVirus();
        }

    }
}

class File implements AbstractFile{

    @Override
    public void killVirus() {
        System.out.println("kill file virus "+this.getClass().getName());
    }
}

