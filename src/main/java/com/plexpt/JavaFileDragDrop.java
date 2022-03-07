package com.plexpt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import cn.hutool.core.io.FileUtil;

public class JavaFileDragDrop extends JFrame {

    JTextArea textArea;

    public JavaFileDragDrop() {
        this.setTitle("文件夹拍平");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screenSize.width / 4, screenSize.height / 4, screenSize.width / 2,
                screenSize.height / 2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        textArea = new JTextArea();
        textArea.setEditable(false);
        DropTargetAdapter targetAdapter = new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable tf = dtde.getTransferable();
                    if (tf.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                        List files = (List) tf.getTransferData(DataFlavor.javaFileListFlavor);
                        if (files.size() > 1) {
                            dtde.rejectDrop();
                            return;
                        }
                        File file = (File) files.get(0);
                        if (!file.isDirectory()) {
                            dtde.rejectDrop();
                            return;
                        }

                        appendText("开始处理" + file.getAbsolutePath());

                        moveFile(file);
                        appendText("处理完成" + file.getAbsolutePath());
//                        textArea.setAutoscrolls(true);
                        dtde.dropComplete(true);
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        DropTarget target = new DropTarget(textArea, DnDConstants.ACTION_COPY_OR_MOVE,
                targetAdapter);

        textArea.setBounds(0, 0, screenSize.width / 2,
                screenSize.height / 2);

        textArea.setLineWrap(true);    //设置文本域中的文本为自动换行
        JScrollPane scrollPane = new JScrollPane();    //将文本域放入滚动窗口
        scrollPane.setViewportView(textArea);
        Dimension size = textArea.getPreferredSize();    //获得文本域的首选大小
        scrollPane.setBounds(1, 1, screenSize.width / 2,
                screenSize.height / 2);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();    //创建一个JPanel对象
        panel.add(scrollPane);    //将JScrollPane添加到JPanel容器中

//        add(panel);    //将JPanel容器添加到JFrame容器中
        setBackground(Color.LIGHT_GRAY);
        setSize(screenSize.width / 2,
                screenSize.height / 2);    //设置JFrame容器的大小
//        frame.setVisible(true);

        this.add(scrollPane);
        textArea.setText("拖放 单个文件夹");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    private void appendText(String text) {
        textArea.append("\n" + text);
    }


    private void moveFile(File dir) {
        File dir2 = new File(dir.getAbsolutePath() + "2");
        FileUtil.mkdir(dir2);
        List<File> files = FileUtil.loopFiles(dir);
        appendText("共" + files.size() + "个文件");

        for (File file : files) {
            String fileName = file.getName();
            appendText(file.getAbsolutePath());

            File file1 = new File(dir2, fileName);

            while (file1.exists()) {
                file1 = new File(dir2, "2" + file1.getName());
            }

            try {
                Files.createLink(FileSystems.getDefault().getPath(file1.getAbsolutePath()),
                        FileSystems.getDefault().getPath(file.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
                appendText(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new JavaFileDragDrop();
    }
}
