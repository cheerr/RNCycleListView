/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React, { Component } from 'react'
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    Dimensions
} from 'react-native'

import RNListView from './RNListView'
const ROWS_IN_DATA_SOURCE = 2000
const dataSource = []
for (let i = 0; i < ROWS_IN_DATA_SOURCE; i++) dataSource.push(`row=${i}`);

class ListViewProject extends Component {
    
    render () {
        
        return (
                <RNListView
                style={styles.container}
                dataSource={dataSource}
                onRowSize={(data, i) => ({
                                         rowHeight: ((i % 20) * 5 + 80)
                                         })}
                renderRow={(i) => {
                return this.renderRow(i)
                }}
                />
                )
    }
    
    renderRow (index) {
        return (
                <View style={{
                flexDirection: 'row',
                flex: 1,
                width: Dimensions.get('window').width,
                height: 40,
                backgroundColor: '#F2F2F2',
                }}>
                
                <Text>{'不同的行高' + index}</Text>
                
                </View>
                )
    }
}

const styles = StyleSheet.create({
                                 container: {
                                 width: Dimensions.get('window').width,
                                 height: Dimensions.get('window').height,
                                 flex: 1,
                                 justifyContent: 'center',
                                 alignItems: 'center',
                                 backgroundColor: '#f7f7f7',
                                 },
                                 })

AppRegistry.registerComponent('ListViewProject', () => ListViewProject);
